class CommandService {
  /** @ngInject */
  constructor($http, dataTranformerService, loggingService) {
    this.http = $http;
    this.dataTranformerService = dataTranformerService;
    this.loggingService = loggingService;
  }

  loadByServer(server, callback) {
    this.loggingService.logDebug('Prepare to load command------------------------------------------------------------');
    this.loggingService.logJson('CommandService', 'loadByServer-filter', server);
    // Make Post request
    const res = this.http.get(`${wsUrl}\/ssh\/cmd\/${server.ipAddress}`);
    res.success((data, status, headers, config) => {
      const sshCommand = data.sSHCommand;
      this.loggingService.logJson('CommandService', 'loadByServer-result', data);
      callback(sshCommand);
    });
    res.error((data, status, headers, config) => {
      this.loggingService.logError(`failure message: ${data}`);
    });
  }

  runCommand(server, command, callback) {
    const data = {
      sshCommand: command
    }
    this.loggingService.logDebug('Prepare to run command------------------------------------------------------------');
    this.loggingService.logJson('CommandService', 'runCommand-FILTER', data);
    // Make Post request
    const res = this.http.post(`${wsUrl}\/ssh\/execCmd\/${server.ipAddress}`, data);
    res.success((data, status, headers, config) => {
      this.loggingService.logJson('CommandService', 'runCommand-RESULT', data);
    });
    res.error((data, status, headers, config) => {
      this.loggingService.logError(`failure message: ${data}`);
    });
  }
}
