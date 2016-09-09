angular
  .module('app', ['ui.router'])
  .service('dataTranformerService', DataTranformerService)
  .service('todoService', TodoService)
  .service('utilService', UtilService)
  .service('zoneService', ZoneService)
  .service('serverService', ServerService);

