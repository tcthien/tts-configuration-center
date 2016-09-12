class ServerService {
  /** @ngInject */
  constructor($http, dataTranformerService, loggingService) {
    this.http = $http;
    this.dataTranformerService = dataTranformerService;
    this.loggingService = loggingService;
  }

  findByZones(fetchedZones, callback) {
    const serverQueryFilter = this.dataTranformerService.buildServerQueryFilter(fetchedZones);
    this.loggingService.logJson('ServerService', 'findByZones', serverQueryFilter);
    // Make Post request
    const res = this.http.post(`${wsUrl}\/server\/query`, serverQueryFilter);
    res.success((data, status, headers, config) => {
      const zoneAndServer = this.dataTranformerService.convertServersFromServerToClient(data);
      this.loggingService.logJson('ServerService', 'findByZones', zoneAndServer);
      callback(zoneAndServer);
    });
    res.error((data, status, headers, config) => {
      this.loggingService.logError(`failure message: ${data}`);
    });
  }

  deleteServer(serverId, callback) {
    this.loggingService.logJson('ServerService', 'deleteServer', serverId);
    const res = this.http.delete(`${wsUrl}\/server\/${serverId}`);
    res.success((data, status, headers, config) => {
      callback();
    });
    res.error((data, status, headers, config) => {
      this.loggingService.logError(`Failure to delete serverId: ${data}`);
    });
  }

  addServer(serverData, callback) {
    const serverObj = {
      server: {
        id: serverData.serverId,
        serverName: serverData.serverName,
        userName: serverData.userName,
        password: serverData.password,
        ipAddress: serverData.ipAddress,
        description: serverData.description,
        zone: {
          id: serverData.zoneId
        }
      }
    };
    this.loggingService.logJson('ServerService', 'addServer', serverObj);
    const res = this.http.post(`${wsUrl}\/server`, serverObj);
    res.success((data, status, headers, config) => {
      callback();
    });
    res.error((data, status, headers, config) => {
      this.loggingService.logError(`failure message: ${data}`);
    });
  }
}
