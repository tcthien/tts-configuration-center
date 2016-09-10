class ServerService {
  /** @ngInject */
  constructor($http, $log, dataTranformerService) {
    this.log = $log;
    this.http = $http;
    this.dataTranformerService = dataTranformerService;
  }
  findByZones(fetchedZones, callback) {
    const serverQueryFilter = this.dataTranformerService.buildServerQueryFilter(fetchedZones);
    // Make Post request
    const res = this.http.post(`${wsUrl}\/server\/query`, serverQueryFilter);
    res.success((data, status, headers, config) => {
      this.log.debug("Request submitted successfully.");
      const zoneAndServer = this.dataTranformerService.convertServersFromServerToClient(data);
      callback(zoneAndServer);
    });
    res.error((data, status, headers, config) => {
      this.log.error(`failure message: ${data}`);
    });
  }
}
