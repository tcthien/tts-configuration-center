class ServerPageController {
  /** @ngInject */
  constructor($scope, $stateParams, loggingService) {
    this.scope = $scope;
    this.loggingService = loggingService;
    this.stateParams = $stateParams;
  }
}

angular
  .module('app')
  .component('serverPage', {
    templateUrl: 'app/containers/ServerPage.html',
    controller: ServerPageController
  });
