class ServerController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, serverService) {
    this.parentScope = $scope.$parent;
    this.log = $log;
    this.serverService = serverService;
    this.rootScope = $rootScope;
    // Unregister listener on root if controller is destroyed
    $scope.$on('$destroy', function () {
      this.serverCreationInfo = {};
    });
    this.data = {};
  }

  deleteServer(serverId) {
    this.serverService.deleteServer(serverId, () => {
      this.rootScope.$emit('reloadZoneData');
    });
  }

  addServer() {
    this.data.zoneId = this.parentScope.selectedZoneId;
    this.serverService.addServer(this.data, () => {
      // FIXME: plz fix to reload only appropriated zone
      this.reset();
      $('#serverCreationDlg').closeModal();
      this.rootScope.$emit('reloadZoneData');
    });
  }

  reset() {
    this.data = {
      serverName: '',
      userName: '',
      password: '',
      ipAddress: '',
      description: ''
    };
  }
}
