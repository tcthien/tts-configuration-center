function openServerCreationDlg() {
  $('#serverCreationDlg').openModal();
}

class ZoneController {
  /** @ngInject */
  constructor($scope, $log, zoneService, $rootScope) {
    this.log = $log;
    this.zoneService = zoneService;
    this.rootScope = $rootScope;
    // Preload Zone from Server
    this.loadAllZone();
    // Register event for reloading new data
    this.rootScope.$on('reloadData', () => {
      this.log.debug('invoke reloadData....');
      this.loadAllZone();
    });
    // Unregister listener on root if controller is destroyed
    $scope.$on('$destroy', function () {
      this.rootScope.$$listeners.reloadData = [];
    });
  }
  loadAllZone() {
    return this.zoneService.loadAllZone(fetchedZones => {
      this.zones = fetchedZones;
    });
  }
}
