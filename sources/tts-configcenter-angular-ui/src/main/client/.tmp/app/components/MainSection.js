'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var MainSection = function () {
  /** @ngInject */
  function MainSection(todoService) {
    _classCallCheck(this, MainSection);

    this.todoService = todoService;
    this.selectedFilter = visibilityFilters[this.filter];
    this.completeReducer = function (count, todo) {
      return todo.completed ? count + 1 : count;
    };
  }

  _createClass(MainSection, [{
    key: 'handleClearCompleted',
    value: function handleClearCompleted() {
      this.todos = this.todoService.clearCompleted(this.todos);
    }
  }, {
    key: 'handleCompleteAll',
    value: function handleCompleteAll() {
      this.todos = this.todoService.completeAll(this.todos);
    }
  }, {
    key: 'handleShow',
    value: function handleShow(filter) {
      this.filter = filter;
      this.selectedFilter = visibilityFilters[filter];
    }
  }, {
    key: 'handleChange',
    value: function handleChange(id) {
      this.todos = this.todoService.completeTodo(id, this.todos);
    }
  }, {
    key: 'handleSave',
    value: function handleSave(e) {
      if (e.text.length === 0) {
        this.todos = this.todoService.deleteTodo(e.id, this.todos);
      } else {
        this.todos = this.todoService.editTodo(e.id, e.text, this.todos);
      }
    }
  }, {
    key: 'handleDestroy',
    value: function handleDestroy(e) {
      this.todos = this.todoService.deleteTodo(e, this.todos);
    }
  }]);

  return MainSection;
}();

angular.module('app').component('mainSection', {
  templateUrl: 'app/components/MainSection.html',
  controller: MainSection,
  bindings: {
    todos: '=',
    filter: '<'
  }
});