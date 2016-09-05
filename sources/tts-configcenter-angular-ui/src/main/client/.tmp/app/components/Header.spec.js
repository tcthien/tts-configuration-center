'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

describe('Header component', function () {
  var todos = [{
    text: 'Use ngrx/store',
    completed: false,
    id: 0
  }];

  var MockTodoService = function () {
    function MockTodoService() {
      _classCallCheck(this, MockTodoService);
    }

    _createClass(MockTodoService, [{
      key: 'addTodo',
      value: function addTodo(text, todos) {
        return [{
          id: todos.length === 0 ? 0 : todos[0].id + 1,
          completed: false,
          text: text
        }].concat(todos);
      }
    }]);

    return MockTodoService;
  }();

  beforeEach(module('app', function ($provide) {
    $provide.factory('todoService', function () {
      return new MockTodoService();
    });
  }));

  beforeEach(module('app', function ($provide) {
    $provide.factory('headerComponent', function () {
      return {
        templateUrl: 'app/components/Header.html'
      };
    });
  }));

  beforeEach(angular.mock.module('app'));

  it('should render correctly', angular.mock.inject(function ($rootScope, $compile) {
    var element = $compile('<header-component></header-component>')($rootScope);
    $rootScope.$digest();
    var header = element.find('h1');
    expect(header.html().trim()).toEqual('todos');
  }));

  it('should get the todos binded to the component', angular.mock.inject(function ($rootScope, $compile, $componentController) {
    var component = $componentController('headerComponent', {}, { todos: todos });
    spyOn(component, 'handleSave').and.callThrough();
    expect(component.todos.length).toEqual(1);
    component.handleSave('New Task');
    expect(component.handleSave).toHaveBeenCalledWith('New Task');
    expect(component.todos.length).toEqual(2);
  }));
});