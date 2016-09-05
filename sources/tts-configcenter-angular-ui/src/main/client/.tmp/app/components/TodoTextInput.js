'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var TodoTextInput = function () {
  /** @ngInject */
  function TodoTextInput(todoService, $window, $timeout) {
    _classCallCheck(this, TodoTextInput);

    this.$timeout = $timeout;
    this.$window = $window;
    this.todoService = todoService;
    this.editing = this.editing || false;
    this.text = this.text || '';
    if (this.text.length) {
      this.focus();
    }
  }

  _createClass(TodoTextInput, [{
    key: 'handleBlur',
    value: function handleBlur() {
      if (!this.newTodo) {
        this.onSave({ text: this.text });
      }
    }
  }, {
    key: 'handleSubmit',
    value: function handleSubmit(e) {
      if (e.keyCode === 13) {
        this.onSave({ text: this.text });
        if (this.newTodo) {
          this.text = '';
        }
      }
    }
  }, {
    key: 'focus',
    value: function focus() {
      var _this = this;

      this.$timeout(function () {
        var element = _this.$window.document.querySelector('.editing .textInput');
        if (element) {
          element.focus();
        }
      }, 0);
    }
  }]);

  return TodoTextInput;
}();

angular.module('app').component('todoTextInput', {
  templateUrl: 'app/components/TodoTextInput.html',
  controller: TodoTextInput,
  bindings: {
    onSave: '&',
    placeholder: '@',
    newTodo: '@',
    editing: '@',
    text: '<'
  }
});