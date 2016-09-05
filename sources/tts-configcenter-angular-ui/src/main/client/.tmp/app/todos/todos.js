'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var initialTodo = {
  text: 'Use AngularJS',
  completed: false,
  id: 0
};

var TodoService = function () {
  function TodoService() {
    _classCallCheck(this, TodoService);
  }

  _createClass(TodoService, [{
    key: 'addTodo',
    value: function addTodo(text, todos) {
      return [{
        id: todos.length === 0 ? 0 : todos[0].id + 1,
        completed: false,
        text: text
      }].concat(todos);
    }
  }, {
    key: 'completeTodo',
    value: function completeTodo(id, todos) {
      return todos.map(function (todo) {
        return todo.id === id ? Object.assign({}, todo, { completed: !todo.completed }) : todo;
      });
    }
  }, {
    key: 'deleteTodo',
    value: function deleteTodo(id, todos) {
      return todos.filter(function (todo) {
        return todo.id !== id;
      });
    }
  }, {
    key: 'editTodo',
    value: function editTodo(id, text, todos) {
      return todos.map(function (todo) {
        return todo.id === id ? Object.assign({}, todo, { text: text }) : todo;
      });
    }
  }, {
    key: 'completeAll',
    value: function completeAll(todos) {
      var areAllMarked = todos.every(function (todo) {
        return todo.completed;
      });
      return todos.map(function (todo) {
        return Object.assign({}, todo, { completed: !areAllMarked });
      });
    }
  }, {
    key: 'clearCompleted',
    value: function clearCompleted(todos) {
      return todos.filter(function (todo) {
        return todo.completed === false;
      });
    }
  }]);

  return TodoService;
}();