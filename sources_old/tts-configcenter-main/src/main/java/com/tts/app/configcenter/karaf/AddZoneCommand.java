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
public class AddZoneCommand extends GenericKarafCommand<Zone, ZoneService> {
    @Argument(index = 0, name = "id", required = true, description = "Zone ID", multiValued = false)
    Integer id;

    @Argument(index = 1, name = "name", required = true, description = "Zone Name", multiValued = false)
    String name;
    
    
    @Override
    protected Zone invoke() throws Exception {
        System.out.println("Executing command addZone");
        service.update(new Zone(id, name));
        System.out.println("Zone added!");
        return null;
    }
}
