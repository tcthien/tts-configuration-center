class UtilService {
  /** @ngInject */
  constructor($log, $http) {
    this.log = $log;
    this.http = $http;
  }
  submitMassCreation(massCreation) {
    this.log.info(`Info ${massCreation.zoneName}, ${massCreation.servers}`);
    // Transform Data & Make Post request
    const obj = {
      massCreation: {
        zoneName: massCreation.zoneName,
        servers: massCreation.servers
      }
    };
    const res = this.http.post(`${wsUrl}\/util\/masscreate`, obj);
    res.success((data, status, headers, config) => {
      this.log.info("Request submitted successfully.");
    });
    res.error((data, status, headers, config) => {
      this.log.error(`failure message: ${data}`);
    });
  }
}
