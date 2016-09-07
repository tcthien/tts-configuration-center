const initialMassCreation = {
  zoneName: 'Your Zone Name...',
  servers: 'Server Name, IP Address, User Name, Password, Description'
};

class ConfigCenterUtilService {
  addTodo(text, todos) {
    return [
      {
        id: (todos.length === 0) ? 0 : todos[0].id + 1,
        completed: false,
        text
      }
    ].concat(todos);
  }
}
