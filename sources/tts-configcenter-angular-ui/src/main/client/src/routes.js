angular
  .module('app')
  .config(routesConfig);

/** @ngInject */
function routesConfig($stateProvider, $urlRouterProvider, $locationProvider) {
  $locationProvider.html5Mode(true).hashPrefix('!');
  $urlRouterProvider.otherwise('/home');

  $stateProvider
    .state('home', {
      url: '/home',
      template: '<home-page></home-page>'
    })
    .state('server', {
      url: '/zone-{zoneId}/server-{serverId}',
      template: '<server-page></server-page>'
    })
    .state('about', {
      url: '/about',
      template: '<about-page></about-page>'
    });
}
