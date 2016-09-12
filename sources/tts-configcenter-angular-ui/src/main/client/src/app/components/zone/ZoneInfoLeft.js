class ZoneInfoLeftController extends ZoneController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, zoneService, dataTranformerService, serverService, $stateParams, loggingService) {
    super($scope, $log, $rootScope, zoneService, dataTranformerService, serverService, loggingService);
    this.serverId = '';
    this.zoneId = '';
    this.allZone = false;
    // URL Params
    if ($stateParams !== null) {
      this.zoneId = $stateParams.zoneId;
      this.scope.serverId = $stateParams.serverId;
    }
  }

  deleteSelectedServer(serverId) {
    this.serverService.deleteServer(serverId, () => {
      this.rootScope.$emit('reloadZoneInfoLeft');
    });
  }
}

angular
  .module('app')
  .component('zoneInfoLeftComponent', {
    templateUrl: 'app/components/zone/ZoneInfoLeft.html',
    controller: ZoneInfoLeftController,
    bindings: {
      zone: '<'
    }
  });
