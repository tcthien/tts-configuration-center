class FeatureService {
  /** @ngInject */
  constructor($http, dataTranformerService, loggingService) {
    this.http = $http;
    this.dataTranformerService = dataTranformerService;
    this.loggingService = loggingService;
  }

  loadByServer(server, callback) {
    this.loggingService.logDebug('Prepare to load feature------------------------------------------------------------');
    this.loggingService.logJson('FeatureService', 'loadByServer-filter', server);
    // Make Post request
    const res = this.http.get(`${wsUrl}\/ssh\/check\/${server.ipAddress}`);
    res.success((data, status, headers, config) => {
      const features = data.sSHFeature;
      this.loggingService.logJson('FeatureService', 'loadByServer-result', data);
      callback(features);
    });
    res.error((data, status, headers, config) => {
      this.loggingService.logError(`failure message: ${data}`);
    });
  }

  installFeature(server, feature, callback) {
    const data = {
      sshFeature: {
        name: feature.name,
        osName: feature.osName,
        osVersion: feature.osVersion
      }
    }
    this.loggingService.logDebug('Prepare to install feature------------------------------------------------------------');
    this.loggingService.logJson('FeatureService', 'installFeature-FILTER', data);
    // Make Post request
    const res = this.http.post(`${wsUrl}\/ssh\/feature\/${server.ipAddress}`, data);
    res.success((data, status, headers, config) => {
      const features = data.sSHFeature;
      this.loggingService.logJson('FeatureService', 'installFeature-RESULT', data);
      callback(features);
    });
    res.error((data, status, headers, config) => {
      this.loggingService.logError(`failure message: ${data}`);
    });
  }

  uninstallFeature(server, feature, callback) {
    const data = {
      sshFeature: {
        name: feature.name,
        osName: feature.osName,
        osVersion: feature.osVersion
      }
    }
    this.loggingService.logDebug('Prepare to uninstall feature------------------------------------------------------------');
    this.loggingService.logJson('FeatureService', 'uninstallFeature-FILTER', data);
    // Make Post request
    var req = this.http({
      method: 'DELETE',
      url: `${wsUrl}\/ssh\/feature\/${server.ipAddress}`,
      headers: {
        'Content-Type': 'application/json'
      },
      data: data
    });
    req.success((data, status, headers, config) => {
      const features = data.sSHFeature;
      this.loggingService.logJson('FeatureService', 'uninstallFeature-RESULT', data);
      callback(features);
    });
    req.error((data, status, headers, config) => {
      this.loggingService.logError(`failure message: ${data}`);
    });
  }
}
