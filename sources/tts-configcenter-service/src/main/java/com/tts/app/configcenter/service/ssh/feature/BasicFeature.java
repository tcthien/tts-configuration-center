package com.tts.app.configcenter.service.ssh.feature;

import java.util.ArrayList;
import java.util.List;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.SSHResultImpl;

public abstract class BasicFeature implements SoftwareFeature {

    protected SSHCommandExecutor executor;
    protected List<SoftwareFeature> dependencies = new ArrayList<>();
    
    public BasicFeature(SSHCommandExecutor executor) {
        this.executor = executor;
    }
    
    public void addDependency(SoftwareFeature dependency) {
        dependencies.add(dependency);
    }
    
    @Override
    public String toString() {
        return getFeatureInfo().toString();
    }

    public SSHCommandExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(SSHCommandExecutor executor) {
        this.executor = executor;
    }
    
    @Override
    public List<SoftwareFeature> getDependencies() {
        return dependencies;
    }
    
    @Override
    public SSHResult install(Server server) throws Exception {
        if (check(server)) {
            return new SSHResultImpl(SSHResult.STATUS_NOK, "Feature is already installed.");
        }
        SSHResult rs = null;
        if (getDependencies() != null && !getDependencies().isEmpty()) {
            rs = installDependencies(server);
        }
        if (rs != null && rs.getExistStatus() == SSHResult.STATUS_NOK) {
            rs.setOutputText("Failed to install dependencies");
            return rs;
        }
        rs = installComponent(server);
        return rs;
    }
    
    @Override
    public SSHResult uninstall(Server server, boolean removeDependency) throws Exception {
        if (!check(server)) {
            return new SSHResultImpl(SSHResult.STATUS_NOK, "Feature is not installed.");
        }
        SSHResult rs = null;
        rs = uninstallComponent(server);
        if (removeDependency && getDependencies() != null && !getDependencies().isEmpty()) {
            rs = uninstallDependencies(server);
        }
        return rs;
    }
    
    protected abstract SSHResult installComponent(Server server) throws Exception;
    
    protected abstract SSHResult uninstallComponent(Server server) throws Exception;
    
    protected SSHResult uninstallDependencies(Server server) throws Exception {
        SSHResult rs = null;
        for (SoftwareFeature dep : getDependencies()) {
            if (dep.check(server)) {
                rs = dep.uninstall(server, true);
            }
        }
        return rs;
    }

    protected SSHResult installDependencies(Server server) throws Exception {
        SSHResult rs = null;
        for (SoftwareFeature dep : getDependencies()) {
            if (!dep.check(server)) {
                rs = dep.install(server);
            }
        }
        return rs;
    }

}
