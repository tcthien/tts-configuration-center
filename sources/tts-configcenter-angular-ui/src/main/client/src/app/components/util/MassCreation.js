class MassCreationController {
  /** @ngInject */
  constructor($scope, $log, utilService, $rootScope) {
    this.log = $log;
    this.utilService = utilService;
    this.rootScope = $rootScope;
    this.massCreation = {
//      zoneName: 'Your Zone Name',
//      servers: 'Server Name, IP Address, user name, password, Description'
    };
  }
  handleSubmit() {
    this.utilService.submitMassCreation(this.massCreation, callback => {
      this.rootScope.$emit('reloadZoneData');
    });
  }
  handleReset() {
    this.massCreation.zoneName = null;
    this.massCreation.servers = null;
  }
}

angular
  .module('app')
  .component('massCreationComponent', {
    templateUrl: 'app/components/util/MassCreation.html',
    controller: MassCreationController,
    bindings: {
    }
  });
