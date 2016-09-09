class UtilService {
  /** @ngInject */
  constructor($log, $http, dataTranformerService) {
    this.log = $log;
    this.http = $http;
    this.dataTranformerService = dataTranformerService;
  }
  submitMassCreation(massCreation, callback) {
    this.log.info(`Info ${massCreation.zoneName}, ${massCreation.servers}`);
    // Transform Data
    const obj = this.dataTranformerService.convertMassCreationFromClientToServer(massCreation);
    // Make Post request
    const res = this.http.post(`${wsUrl}\/util\/masscreate`, obj);
    res.success((data, status, headers, config) => {
      this.log.info("Request submitted successfully.");
      callback();
    });
    res.error((data, status, headers, config) => {
      this.log.error(`failure message: ${data}`);
    });
  }
}
