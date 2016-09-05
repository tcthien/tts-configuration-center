'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

describe('MainSection component', function () {
  var MockTodoService = function () {
    function MockTodoService() {
      _classCallCheck(this, MockTodoService);
    }

    _createClass(MockTodoService, [{
      key: 'addTodo',
      value: function addTodo() {}
    }, {
      key: 'editTodo',
      value: function editTodo() {}
    }, {
      key: 'deleteTodo',
      value: function deleteTodo() {}
    }, {
      key: 'completeTodo',
      value: function completeTodo() {}
    }, {
      key: 'completeAll',
      value: function completeAll() {}
    }, {
      key: 'clearCompleted',
      value: function clearCompleted() {}
    }]);

    return MockTodoService;
  }();

  var component = void 0;

  beforeEach(module('app', function ($provide) {
    $provide.factory('mainSection', function () {
      return {
        templateUrl: 'app/components/MainSection.html'
      };
    });
  }));

  beforeEach(module('app', function ($provide) {
    $provide.factory('todoService', function () {
      return new MockTodoService();
    });
  }));

  beforeEach(angular.mock.module('app'));

  beforeEach(angular.mock.inject(function ($componentController) {
    component = $componentController('mainSection', {}, {});
  }));

  it('shoud call clearCompleted', function () {
    spyOn(component.todoService, 'clearCompleted').and.callThrough();
    component.handleClearCompleted();
    expect(component.todoService.clearCompleted).toHaveBeenCalled();
  });

  it('shoud call completeAll', function () {
    spyOn(component.todoService, 'completeAll').and.callThrough();
    component.handleCompleteAll();
    expect(component.todoService.completeAll).toHaveBeenCalled();
  });

  it('shoud set selectedFilter', function () {
    component.handleShow('show_completed');
    expect(component.selectedFilter.type).toEqual('show_completed');
    expect(component.selectedFilter.filter({ completed: true })).toEqual(true);
  });

  it('shoud call completeTodo', function () {
    spyOn(component.todoService, 'completeTodo').and.callThrough();
    component.handleChange();
    expect(component.todoService.completeTodo).toHaveBeenCalled();
  });

  it('shoud call deleteTodo', function () {
    spyOn(component.todoService, 'deleteTodo').and.callThrough();
    component.handleSave({ text: '' });
    expect(component.todoService.deleteTodo).toHaveBeenCalled();
  });

  it('shoud call editTodo', function () {
    spyOn(component.todoService, 'editTodo').and.callThrough();
    component.handleSave({ text: 'Hello' });
    expect(component.todoService.editTodo).toHaveBeenCalled();
  });

  it('shoud call deleteTodo', function () {
    spyOn(component.todoService, 'deleteTodo').and.callThrough();
    component.handleDestroy();
    expect(component.todoService.deleteTodo).toHaveBeenCalled();
  });
});