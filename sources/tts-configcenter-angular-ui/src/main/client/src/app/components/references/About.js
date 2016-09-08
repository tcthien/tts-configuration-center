angular
  .module('app')
  .component('referenceAboutComponent', {
    templateUrl: 'app/components/references/About.html',
//    controller: ZoneController,
    bindings: {
      onSave: '&'
    }
  });
