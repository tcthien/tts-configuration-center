class ServerCreationController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, serverService, loggingService) {
    this.parentScope = $scope.$parent;
    this.log = $log;
    this.serverService = serverService;
    this.rootScope = $rootScope;
    this.loggingService = loggingService;
    // Unregister listener on root if controller is destroyed
    $scope.$on('$destroy', () => {
      this.serverCreationInfo = {};
    });
  }

  addServer() {
    let zoneId = null;
    if (this.zone === null) {
      zoneId = this.rootScope.selectedZoneId;
    } else {
      zoneId = this.zone.id;
    }
    const data = {
      zoneId: zoneId,
      serverId: this.server.id,
      serverName: this.server.serverName,
      userName: this.server.userName,
      password: this.server.password,
      ipAddress: this.server.ipAddress,
      description: this.server.description
    }
    this.loggingService.logDebug('ServerCreationController.addServer() --------------------------------------------');
    this.loggingService.logJson('ServerCreationController', 'addServer-', data);
    this.serverService.addServer(data, () => {
      // FIXME: plz fix to reload only appropriated zone
      this.reset();
      $('#serverCreationDlg').closeModal();
      if (this.parent == 'ServerPage') {
        this.rootScope.$emit('reloadZoneInfoLeft');
      } else if (this.parent == 'HomePage') {
        this.rootScope.$emit('reloadZoneData');
      }
    });
  }

  reset() {
    this.server = {
      serverName: '',
      userName: '',
      password: '',
      ipAddress: '',
      description: ''
    };
  }
}

angular
  .module('app')
  .component('createServerComponent', {
    templateUrl: 'app/components/server/ServerCreation.html',
    controller: ServerCreationController,
    bindings: {
      server: '=',
      zone: '<',
      parent: '<'
    }
  });
