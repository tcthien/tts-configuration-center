class ZoneController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, zoneService, dataTranformerService, serverService) {
    this.log = $log;
    this.rootScope = $rootScope;
    this.zoneService = zoneService;
    this.dataTranformerService = dataTranformerService;
    this.serverService = serverService;
    this.scope = $scope;

    // Preload Zone from Server
    this.reloadAllZone();
    // Register event for reloading new data
    this.rootScope.$on('reloadZoneData', () => {
      this.reloadAllZone();
    });
    // Unregister listener on root if controller is destroyed
    $scope.$on('$destroy', function () {
      this.rootScope.$$listeners.reloadZoneData = [];
    });
  }

  reloadAllZone() {
    return this.zoneService.loadAllZone(fetchedZones => {
      // From fetchedZones, we will invoke to server to load server
      const servers = this.serverService.findByZones(fetchedZones, zoneAndServers => {
        this.zoneAndServers = zoneAndServers;
      });
    });
  }

  openServerCreationDlg(zoneId) {
    this.scope.selectedZoneId = zoneId;
    $('#serverCreationDlg').openModal();
  }
}
