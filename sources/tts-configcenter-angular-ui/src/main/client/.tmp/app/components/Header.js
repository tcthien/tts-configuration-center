'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Header = function () {
  /** @ngInject */
  function Header(todoService) {
    _classCallCheck(this, Header);

    this.todoService = todoService;
  }

  _createClass(Header, [{
    key: 'handleSave',
    value: function handleSave(text) {
      if (text.length !== 0) {
        this.todos = this.todoService.addTodo(text, this.todos);
      }
    }
  }]);

  return Header;
}();

angular.module('app').component('headerComponent', {
  templateUrl: 'app/components/Header.html',
  controller: Header,
  bindings: {
    todos: '='
  }
});