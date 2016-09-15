class FeatureListController {
  /** @ngInject */
  constructor($scope, $log, $rootScope, loggingService, featureService, commandService, $interval, $http) {
    this.parentScope = $scope.$parent;
    this.log = $log;
    this.rootScope = $rootScope;
    this.loggingService = loggingService;
    this.featureService = featureService;
    this.commandService = commandService;
    this.http = $http;
    this.consoleLog = "";
    $scope.$on('$destroy', () => {
      this.server = null;
      this.interval.cancel(this.intervalFunction);
    });

    this.interval = $interval;
    this.intervalFunction = $interval(() => {
      if (this.parentScope.server != null) {
        const res = this.http.get(`${wsUrl}\/ssh\/log\/${this.parentScope.server.ipAddress}`);
        res.success((data, status, headers, config) => {
          if(data.consoleLog.log != "") {
            this.consoleLog += data.consoleLog.log;
          }
        });
        res.error((data, status, headers, config) => {
          this.loggingService.logError(`failure message: ${data}`);
        });
      }
    }, 500);
  }
  

  runCommand(server, command) {
    switchTab('#commandContainter');
    this.commandService.runCommand(server, command, () => {
      // Request finished => reload feature table
      //this.rootScope.$emit('reloadCommands');
      Materialize.toast('Command has been submitted to server', 2000);
    });
  }

  installFeature(server, feature) {
    switchTab('#featureContainter');
    this.featureService.installFeature(server, feature, () => {
      // Request finished => reload feature table
      Materialize.toast('Feature has been installed', 2000);
      this.rootScope.$emit('reloadFeatures');
    });
  }

  uninstallFeature(server, feature) {
    switchTab('#featureContainter');
    this.featureService.uninstallFeature(server, feature, () => {
      // Request finished => reload feature table
      Materialize.toast('Feature has been uninstalled', 2000);
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
