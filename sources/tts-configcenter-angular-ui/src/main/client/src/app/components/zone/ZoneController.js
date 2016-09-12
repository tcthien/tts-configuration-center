class ZoneController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, zoneService, dataTranformerService, serverService) {
    this.log = $log;
    this.rootScope = $rootScope;
    this.zoneService = zoneService;
    this.dataTranformerService = dataTranformerService;
    this.serverService = serverService;
    this.scope = $scope;

    // Register event for reloading new data
    this.rootScope.$on('reloadZoneData', () => {
      this.reloadAllZone();
    });
    // Unregister listener on root if controller is destroyed
    $scope.$on('$destroy', () => {
      this.rootScope.$$listeners.reloadZoneData = [];
    });
  }

  showSimpleMode() {
    this.scope.simpleMode = true;
  }

  showNormalMode() {
    this.scope.simpleMode = false;
  }

  loadZones(zoneId) {
    return this.zoneService.loadZones(zoneId, fetchedZones => {
      this.loadServerByZone(fetchedZones);
    });
  }

  loadServerByZone(fetchedZones) {
    // From fetchedZones, we will invoke to server to load server
    const servers = this.serverService.findByZones(fetchedZones, zoneAndServers => {
      this.zoneAndServers = zoneAndServers;
    });
  }

  openServerCreationDlg(zoneId) {
    this.scope.selectedZoneId = zoneId;
    $('#serverCreationDlg').openModal();
  }
}
