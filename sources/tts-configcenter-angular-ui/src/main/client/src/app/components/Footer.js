class Footer {
  constructor() {
  }

  handleClear() {
  }

  handleChange(filter) {
  }
}

angular
  .module('app')
  .component('footerComponent', {
    templateUrl: 'app/components/Footer.html',
    controller: Footer
  });

