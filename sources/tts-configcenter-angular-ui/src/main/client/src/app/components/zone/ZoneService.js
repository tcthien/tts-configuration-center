class ZoneService {
  /** @ngInject */
  constructor($http, $log, dataTranformerService) {
    this.log = $log;
    this.http = $http;
    this.dataTranformerService = dataTranformerService;
  }
  loadAllZone(callback) {
    const res = this.http.get(`${wsUrl}\/zone`);
    res.success((data, status, headers, config) => {
      // Log data load from server
      this.log.debug(`loadAllZone-server: ${JSON.stringify(data)}`);
      callback(this.transformData(data));
    });
    res.error((data, status, headers, config) => {
      this.log.error(`failure message: ${data}`);
    });
  }
  transformData(zoneServerList) {
    return this.dataTranformerService.convertZoneFromServerToClient(zoneServerList);
  }
}
