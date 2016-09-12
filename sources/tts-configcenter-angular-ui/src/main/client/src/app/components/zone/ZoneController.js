class ZoneController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, zoneService, dataTranformerService, serverService, loggingService) {
    this.log = $log;
    this.rootScope = $rootScope;
    this.zoneService = zoneService;
    this.dataTranformerService = dataTranformerService;
    this.loggingService = loggingService;
    this.serverService = serverService;
    this.scope = $scope;
    this.allZone = true;

    // Register event for reloading new data
    this.rootScope.$on('reloadZoneData', () => {
      if (this.allZone) {
        this.loadZones(null);
      } else {
        // In this case, data was loaded from ServerPageController so notify the listener
        this.rootScope.$emit('reloadZoneInfoLeft');
      }
    });
    // Unregister listener on root if controller is destroyed
    $scope.$on('$destroy', () => {
      this.rootScope.$$listeners.reloadZoneData = [];
      this.rootScope.selectedZoneId = null;
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
    this.rootScope.selectedZoneId = zoneId;
    this.loggingService.logJson('ZoneController', 'openServerCreationDlg', zoneId);
    $('#serverCreationDlg').openModal();
  }
}
