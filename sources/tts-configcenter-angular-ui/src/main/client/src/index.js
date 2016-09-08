angular
  .module('app', ['ui.router'])
  .service('todoService', TodoService)
  .service('utilService', UtilService)
  .service('zoneService', ZoneService)
  .service('serverService', ServerService);
