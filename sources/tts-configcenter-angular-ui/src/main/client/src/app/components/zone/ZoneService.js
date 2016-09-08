class ZoneService {
  /** @ngInject */
  constructor($http, $log) {
    this.log = $log;
    this.http = $http;
  }
  loadAllZone(callback) {
    const res = this.http.get(`${wsUrl}\/zone`);
    res.success((data, status, headers, config) => {
      // Log data load from server
      this.log.debug(`loadAllZone-server: ${JSON.stringify(data)}`);
      callback(this.transform(data));
    });
    res.error((data, status, headers, config) => {
      this.log.error(`failure message: ${data}`);
    });
  }
  transform(zoneServerList) {
    const rs = [];
    const zones = zoneServerList.zone;
    let i = 0;
    for (const zone of zones) {
      rs[i++] = zone;
    }
    this.log.debug(`loadAllZone-transform: ${JSON.stringify(rs)}`);
    return rs;
  }
}
