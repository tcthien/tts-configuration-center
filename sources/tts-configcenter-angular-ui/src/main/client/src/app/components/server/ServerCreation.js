angular
  .module('app')
  .component('createServerComponent', {
    templateUrl: 'app/components/server/ServerCreation.html',
    controller: ServerController,
    bindings: {
      onSave: '&'
    }
  });
