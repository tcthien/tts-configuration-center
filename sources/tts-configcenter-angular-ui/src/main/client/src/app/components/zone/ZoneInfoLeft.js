class ZoneInfoLeftController extends ZoneController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, zoneService, dataTranformerService, serverService, $stateParams) {
    super($scope, $log, $rootScope, zoneService, dataTranformerService, serverService);
    this.serverId = '';
    this.zoneId = '';
    // URL Params
    if ($stateParams !== null) {
      this.zoneId = $stateParams.zoneId;
      this.scope.serverId = $stateParams.serverId;
    }
  }
}

angular
  .module('app')
  .component('zoneInfoLeftComponent', {
    templateUrl: 'app/components/zone/ZoneInfoLeft.html',
    controller: ZoneInfoLeftController
  });
