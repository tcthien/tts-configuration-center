class ServerPageController {
  /** @ngInject */
  constructor($rootScope, $scope, $stateParams, loggingService, zoneService, serverService, featureService, commandService) {
    this.rootScope = $rootScope;
    this.scope = $scope;
    this.loggingService = loggingService;
    this.zoneService = zoneService;
    this.serverService = serverService;
    this.featureService = featureService;
    this.commandService = commandService;
    this.stateParams = $stateParams;
    // URL Params
    if ($stateParams !== null) {
      this.rootScope.serverId = $stateParams.serverId;
      this.initialize($stateParams.zoneId, $stateParams.serverId);
    }

    // Register event for reloading new data
    this.rootScope.$on('reloadZoneInfoLeft', () => {
      this.initialize(this.stateParams.zoneId, this.stateParams.serverId);
    });
    this.rootScope.$on('reloadFeatures', () => {
      this.reloadFeatures(this.server);
    });
    /*
    this.rootScope.$on('reloadCommands', () => {
      this.reloadCommands(this.server);
    });*/
    // Unregister listener on root if controller is destroyed
    $scope.$on('$destroy', () => {
      this.rootScope.$$listeners.reloadZoneInfoLeft = [];
      this.rootScope.$$listeners.reloadFeatures = [];
      //this.rootScope.$$listeners.reloadCommands = [];
      this.rootScope.serverId = null;
    });
  }

  initialize(zoneId, serverId) {
    this.zoneService.loadZones(zoneId, fetchedZones => {
      // loadZones success -> going to load server
      this.serverService.findByZones(fetchedZones, zoneAndServers => {
        // loadServer success -> update selected server
        this.zoneAndServers = zoneAndServers;
        this.zone = zoneAndServers[0];
        for (const server of this.zone.servers) {
          if (server.id == serverId) {
            this.server = server;
            this.scope.server = server;
            this.reloadFeaturesAndCommands(this.server);
            break;
          }
        }
        
      });
    });
  }

  reloadFeatures(server) {
    this.featureService.loadByServer(server, features => {
      this.features = features;
    });
  }

  reloadCommands(server) {
    this.commandService.loadByServer(server, commands => {
      this.commands = commands;
    });
  }

  reloadFeaturesAndCommands(server) {
    // If finding server -> load features
    this.loggingService.logDebug('Prepare to load Feature ----------------------------------------------------');
    this.loggingService.logJson('ServerPageController', 'loadFeatures-For', server);
    this.reloadFeatures(server);
    this.reloadCommands(server);
  }

  openServerCreationDlgForEdit() {
    $('#serverCreationDlg').openModal();
  }
}

angular
  .module('app')
  .component('serverPage', {
    templateUrl: 'app/containers/ServerPage.html',
    controller: ServerPageController
  });
