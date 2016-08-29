package com.tts.app.configcenter.karaf;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

import com.tts.app.configcenter.zone.Zone;
import com.tts.app.configcenter.zone.ZoneService;
import com.tts.lib.karaf.command.GenericKarafCommand;

/**
 * Displays the last log entries
 */
@Command(scope = "configcenter", name = "addzone", description = "Add Zone to Service")
public class DeleteZoneCommand extends GenericKarafCommand<Zone, ZoneService> {
    @Argument(index = 0, name = "id", required = true, description = "Zone ID", multiValued = false)
    Integer id;

    @Override
    protected Zone invoke() throws Exception {
        System.out.println("Executing command deleteZone");
        service.delete(id);
        System.out.println("Zone deleted!");
        return null;
    }
}
