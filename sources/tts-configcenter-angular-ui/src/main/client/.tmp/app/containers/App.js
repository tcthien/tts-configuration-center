'use strict';

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var App = function App() {
  _classCallCheck(this, App);

  this.todos = [initialTodo];
  this.filter = SHOW_ALL;
};

angular.module('app').component('app', {
  templateUrl: 'app/containers/App.html',
  controller: App
});