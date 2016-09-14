class FeatureListController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, loggingService, featureService, commandService) {
    this.parentScope = $scope.$parent;
    this.log = $log;
    this.rootScope = $rootScope;
    this.loggingService = loggingService;
    this.featureService = featureService;
    this.commandService = commandService;
    $scope.$on('$destroy', () => {
      this.server = null;
    });
  }

  runCommand(server, command) {
    this.commandService.runCommand(server, command, () => {
      // Request finished => reload feature table
      //this.rootScope.$emit('reloadCommands');
    });
  }

  installFeature(server, feature) {
    this.featureService.installFeature(server, feature, () => {
      // Request finished => reload feature table
      this.rootScope.$emit('reloadFeatures');
    });
  }

  uninstallFeature(server, feature) {
    this.featureService.uninstallFeature(server, feature, () => {
      // Request finished => reload feature table
      this.rootScope.$emit('reloadFeatures');
    });
  }
}

angular
  .module('app')
  .component('featureListComponent', {
    templateUrl: 'app/components/feature/FeatureList.html',
    controller: FeatureListController,
    bindings: {
      features: '<',
      commands: '<'
    }
  });
