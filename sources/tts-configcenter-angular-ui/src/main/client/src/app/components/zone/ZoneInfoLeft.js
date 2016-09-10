class ZoneInfoLeftController extends ZoneController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, zoneService, dataTranformerService, serverService, $stateParams) {
    super($scope, $log, $rootScope, zoneService, dataTranformerService, serverService);
    this.serverId = '';
    this.zoneId = '';
    // URL Params
    this.serverId = $stateParams.serverId;
    this.zoneId = $stateParams.zoneId;
  }
}

angular
  .module('app')
  .component('zoneInfoLeftComponent', {
    templateUrl: 'app/components/zone/ZoneInfoLeft.html',
    controller: ZoneInfoLeftController
  });
