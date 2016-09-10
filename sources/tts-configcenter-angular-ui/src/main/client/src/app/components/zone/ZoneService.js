class ZoneService {
  /** @ngInject */
  constructor($http, $log, dataTranformerService, loggingService) {
    this.log = $log;
    this.http = $http;
    this.dataTranformerService = dataTranformerService;
    this.loggingService = loggingService;
  }
  loadZones(zoneId, callback) {
    let res = null;
    if (zoneId === null) {
      res = this.http.get(`${wsUrl}\/zone`);
      res.success((data, status, headers, config) => {
        // Log data load from server
        this.loggingService.logJson("ZoneService", "loadZones", data);
        callback(this.transformData(data));
      });
    } else {
      res = this.http.get(`${wsUrl}\/zone\/${zoneId}`);
      res.success((data, status, headers, config) => {
        // Log data load from server
        this.loggingService.logJson("ZoneService", "loadZones", data);
        // FIXME: moving to data tranformer
        const arr = {
          zone: [data.zone]
        };
        this.loggingService.logJson("ZoneService", "loadZones", arr);
        callback(this.transformData(arr));
      });
    }
    res.error((data, status, headers, config) => {
      this.log.error(`failure message: ${data}`);
    });
  }
  transformData(zoneServerList) {
    return this.dataTranformerService.convertZoneFromServerToClient(zoneServerList);
  }
}
