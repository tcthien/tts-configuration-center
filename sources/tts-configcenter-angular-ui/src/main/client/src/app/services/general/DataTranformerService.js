class DataTranformerService {
  /** @ngInject */
  constructor($log) {
    this.log = $log;
  }
  /** Transformer for MassCreation ---------------------------------------------------------------- */
  convertMassCreationFromClientToServer(massCreation) {
    return {
      massCreation: {
        zoneName: massCreation.zoneName,
        servers: massCreation.servers
      }
    };
  }
  /** Transformer for Server ---------------------------------------------------------------------- */
  buildServerQueryFilter(fetchedZones) {
    const ids = this.getZoneIds(fetchedZones);
    const rs = {
      serverQueryFilter: {
        zoneIds: ids
      }
    };
    this.log.debug(`DataTranformerService-buildServerQueryFilter: ${JSON.stringify(rs)}`);
    return rs;
  }
  convertServersFromServerToClient(fetchedServers) {
    // zoneArray
    const zoneArray = [];
    // Key: zoneId, value: server arrays
    const serverMap = [];
    const servers = fetchedServers.server;
    for (const server of servers) {
      // Update zone to zoneArray
      const zone = {
        id: server.zone.id,
        zoneName: server.zone.zoneName
      };
      zoneArray.push(zone);
      // Update server to serverMap
      if (serverMap[zone.id] === null || serverMap[zone.id] === undefined) {
        serverMap[zone.id] = [];
      }
      serverMap[zone.id].push(server);
    }
    for (const zone of zoneArray) {
      zone.servers = serverMap[zone.id];
    }
    this.log.debug(`DataTranformerService-convertServersFromServerToClient: ${JSON.stringify(zoneArray)}`);
    return zoneArray;
  }
  /** Transformer for Zone ------------------------------------------------------------------------ */
  convertZoneFromServerToClient(zoneServerList) {
    const rs = [];
    const zones = zoneServerList.zone;
    for (const zone of zones) {
      rs.push(zone);
    }
    this.log.debug(`DataTranformerService-convertZoneFromServerToClient: ${JSON.stringify(rs)}`);
    return rs;
  }
  /** Util method --------------------------------------------------------------------------------- */
  getZoneIds(fetchedZones) {
    const rs = [];
    for (const zone of fetchedZones) {
      rs.push(zone.id);
    }
    this.log.debug(`DataTranformerService-getZoneIds: ${JSON.stringify(rs)}`);
    return rs;
  }
}
