package com.tts.app.configcenter.service.resource;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.tts.app.configcenter.model.ssh.SSHStatus;
import com.tts.app.configcenter.service.ssh.CmdStatus;
import com.tts.app.configcenter.service.ssh.SSHService;

@Named(value = "sshResourceImpl")
@Consumes({ "application/json", "test/xml" })
@Produces({ "application/json", "test/xml" })
public class SSHResourceImpl extends LogicResourceImpl {

    @Inject
    SSHService sshService;

    @GET
    @Path("/ping/{srcIpAddress}/{desIpAddress}")
    public Response ping(@PathParam("srcIpAddress") String srcIpAddress, @PathParam("desIpAddress") String desIpAddress) {
        try {
            Boolean status = sshService.ping(srcIpAddress, desIpAddress);
            SSHStatus pingStatus = status != null ? new SSHStatus(status.toString()) : null;
            return pingStatus == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(pingStatus).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/check")
    public Response checkFeatureStatus() {
        Map<String, Boolean> rs = sshService.checkFeatureStatus();
        return rs == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(rs).build();
    }

    @POST
    @Path("/feature/{ipAddress}/{featureName}")
    public Response installFeature(@PathParam("ipAddress") String ipAddress, @PathParam("featureName") String featureName) {
        CmdStatus rs = sshService.installFeature(ipAddress, featureName);
        return rs == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(rs).build();
    }

    @DELETE
    @Path("/feature/{ipAddress}/{featureName}")
    public Response uninstallFeature(@PathParam("ipAddress") String ipAddress, @PathParam("featureName") String featureName) {
        CmdStatus rs = sshService.installFeature(ipAddress, featureName);
        return rs == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(rs).build();
    }
}