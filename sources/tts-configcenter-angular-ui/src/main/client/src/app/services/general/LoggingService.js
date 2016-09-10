class LoggingService {
  /** @ngInject */
  constructor($log) {
    this.log = $log;
  }

  logJson(className, methodName, data) {
    this.log.debug(`${className}-${methodName}: ${JSON.stringify(data)}`);
  }

  logDebug(debugMessage) {
    this.log.debug(debugMessage);
  }

  logError(debugMessage) {
    this.log.error(debugMessage);
  }
}
