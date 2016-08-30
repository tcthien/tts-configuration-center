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
import com.tts.app.configcenter.service.ssh.CmdStatus;
import com.tts.app.configcenter.service.ssh.SSHService;
import com.tts.app.configcenter.service.ssh.feature.CmdFeature;

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
    @Path("/feature")
    public List<SSHFeature> getSupportedFeature() {
        List<SSHFeature> rs = new ArrayList<>();
        
        List<CmdFeature> features = sshService.getSupportedFeatures();
        for (CmdFeature feature : features) {
            rs.add(convertToSSHFeature(feature));
        }
        return rs;
    }

    protected SSHFeature convertToSSHFeature(CmdFeature feature) {
        SSHFeature tmp = new SSHFeature();
        tmp.setName(feature.getName());
        tmp.setDescription(feature.getDescription());
        tmp.setOsName(feature.getOSName());
        tmp.setOsVersion(feature.getOSVersion());
        return tmp;
    }
    
    @GET
    @Path("/check/{ipAddress}")
    public List<SSHFeature> checkFeatureStatus(@PathParam("ipAddress") String ipAddress) throws Exception {
        List<SSHFeature> rs = new ArrayList<>();
        
        Map<CmdFeature, Boolean> status = sshService.checkFeatureStatus(ipAddress);
        for (Entry<CmdFeature, Boolean> entry : status.entrySet()) {
            SSHFeature sshFeature = convertToSSHFeature(entry.getKey());
            sshFeature.setStatus(entry.getValue());
            rs.add(sshFeature);
        }
        return rs;
    }

    
    
    @POST
    @Path("/feature/{ipAddress}/{featureName}")
    public Response installFeature(@PathParam("ipAddress") String ipAddress, @PathParam("featureName") String featureName) throws Exception {
        CmdStatus rs = sshService.installFeature(ipAddress, featureName);
        return rs == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(rs).build();
    }

    @DELETE
    @Path("/feature/{ipAddress}/{featureName}")
    public Response uninstallFeature(@PathParam("ipAddress") String ipAddress, @PathParam("featureName") String featureName) throws Exception {
        CmdStatus rs = sshService.installFeature(ipAddress, featureName);
        return rs == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(rs).build();
    }
}
