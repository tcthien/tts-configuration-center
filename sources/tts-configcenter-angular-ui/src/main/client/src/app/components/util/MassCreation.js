class MassCreationController {
  /** @ngInject */
  constructor($scope, $log, utilService) {
    this.log = $log;
    this.utilService = utilService;
    this.massCreation = {
      zoneName: 'Your Zone Name',
      servers: 'Name, IP Address, user name, password, Description'
    };
  }
  handleSubmit() {
    this.utilService.submitMassCreation(this.massCreation);
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
