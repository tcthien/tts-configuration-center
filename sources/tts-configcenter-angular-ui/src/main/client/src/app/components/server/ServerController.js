class ServerController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, serverService) {
    this.parentScope = $scope.$parent;
    this.log = $log;
    this.serverService = serverService;
    this.rootScope = $rootScope;
    // Unregister listener on root if controller is destroyed
    $scope.$on('$destroy', () => {
      this.serverCreationInfo = {};
    });
    this.data = {};
  }

  isSimpleMode() {
    return this.parentScope.simpleMode;
  }

  isSelectedServer(serverId) {
    return this.rootScope.serverId == serverId;
  }

  deleteServer(serverId) {
    this.serverService.deleteServer(serverId, () => {
      this.rootScope.$emit('reloadZoneData');
    });
  }
}
