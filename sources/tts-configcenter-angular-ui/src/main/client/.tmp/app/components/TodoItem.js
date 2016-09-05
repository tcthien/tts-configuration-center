'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var TodoItem = function () {
  function TodoItem() {
    _classCallCheck(this, TodoItem);

    this.editing = false;
  }

  _createClass(TodoItem, [{
    key: 'handleDoubleClick',
    value: function handleDoubleClick() {
      this.editing = true;
    }
  }, {
    key: 'handleSave',
    value: function handleSave(text) {
      this.onSave({
        todo: {
          text: text,
          id: this.todo.id
        }
      });
      this.editing = false;
    }
  }, {
    key: 'handleDestroy',
    value: function handleDestroy(id) {
      this.onDestroy({ id: id });
    }
  }]);

  return TodoItem;
}();

angular.module('app').component('todoItem', {
  templateUrl: 'app/components/TodoItem.html',
  controller: TodoItem,
  bindings: {
    todo: '<',
    onDestroy: '&',
    onChange: '&',
    onSave: '&'
  }
});