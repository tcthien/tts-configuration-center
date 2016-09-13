angular
  .module('app', ['ui.router'])
  .service('loggingService', LoggingService)
  .service('dataTranformerService', DataTranformerService)
  .service('todoService', TodoService)
  .service('utilService', UtilService)
  .service('zoneService', ZoneService)
  .service('featureService', FeatureService)
  .service('serverService', ServerService);

