package com.tts.app.configcenter.service.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.tts.app.configcenter.model.ssh.SSHFeature;
import com.tts.app.configcenter.model.ssh.SSHStatus;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.SSHService;
import com.tts.app.configcenter.service.ssh.feature.SoftwareFeature;

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
            SSHStatus pingStatus = convertToSSHStatus(status);
            return Response.ok(pingStatus).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    protected SSHStatus convertToSSHStatus(Boolean status) {
        SSHStatus pingStatus = status != null ? new SSHStatus(status.toString()) : null;
        return pingStatus;
    }
    
    @GET
    @Path("/feature")
    public List<SSHFeature> getSupportedFeature() {
        List<SSHFeature> rs = new ArrayList<>();
        
        List<SoftwareFeature> features = sshService.getSupportedFeatures();
        for (SoftwareFeature feature : features) {
            rs.add(feature.getFeatureInfo());
        }
        return rs;
    }

    @GET
    @Path("/check/{ipAddress}")
    public List<SSHFeature> checkFeatureStatus(@PathParam("ipAddress") String ipAddress) throws Exception {
        List<SSHFeature> rs = new ArrayList<>();
        
        Map<SoftwareFeature, Boolean> status = sshService.checkFeatureStatus(ipAddress);
        for (Entry<SoftwareFeature, Boolean> entry : status.entrySet()) {
            SSHFeature sshFeature = entry.getKey().getFeatureInfo();
            sshFeature.setStatus(entry.getValue());
            rs.add(sshFeature);
        }
        return rs;
    }
    
    @POST
    @Path("/feature/{ipAddress}")
    public Response installFeature(@PathParam("ipAddress") String ipAddress, SSHFeature feature) throws Exception {
        SSHResult rs = sshService.installFeature(ipAddress, feature);
        SSHStatus status = new SSHStatus(rs.getExistStatus() == SSHResult.STATUS_OK ? "ok" : "nok");
        return Response.ok(status).build();
    }

    @DELETE
    @Path("/feature/{ipAddress}")
    public Response uninstallFeature(@PathParam("ipAddress") String ipAddress, SSHFeature feature) throws Exception {
        SSHResult rs = sshService.uninstallFeature(ipAddress, feature, false);
        SSHStatus status = new SSHStatus(rs.getExistStatus() == SSHResult.STATUS_OK ? "ok" : "nok");
        return Response.ok(status).build();
    }
    
    @DELETE
    @Path("/feature/{ipAddress}/dependencies")
    public Response uninstallFeatureAndDependencies(@PathParam("ipAddress") String ipAddress, SSHFeature feature) throws Exception {
        SSHResult rs = sshService.uninstallFeature(ipAddress, feature, true);
        SSHStatus status = new SSHStatus(rs.getExistStatus() == SSHResult.STATUS_OK ? "ok" : "nok");
        return Response.ok(status).build();
    }
}
