package com.tts.app.cc.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@OsgiServiceProvider(classes = { SSHCommandExecutor.class })
// The properties below allow to transparently export the service as a web
// service using Distributed OSGi
@Properties({ @Property(name = "service.exported.interfaces", value = "*") })
@Named
public class SSHCommandExecutorImpl implements SSHCommandExecutor {

    public SSHResult execute(String host, String userName, String password, SSHCommand cmd) throws JSchException, IOException {
        Map<SSHCommand, SSHResult> rs = execute(host, userName, password, Arrays.asList(cmd));
        return rs.values().iterator().next();
    }

    public Map<SSHCommand, SSHResult> execute(String host, String userName, String password, List<SSHCommand> cmds)
            throws JSchException, IOException {
        Map<SSHCommand, SSHResult> rs = new LinkedHashMap<SSHCommand, SSHResult>();

        Session session = null;
        try {

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session = jsch.getSession(userName, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();

            for (SSHCommand sshCommand : cmds) {
                SSHResult sshResult = new SSHResultImpl();
                rs.put(sshCommand, sshResult);

                Channel channel = null;
                channel = session.openChannel("exec");
                try {
                    ((ChannelExec) channel).setCommand(sshCommand.getTextCommand());
                    System.out.println("Execute Command: " + sshCommand.getTextCommand());
                    channel.setInputStream(null);
                    ((ChannelExec) channel).setErrStream(System.err);

                    InputStream in = channel.getInputStream();
                    channel.connect();
                    byte[] tmp = new byte[1024];
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        while (in.available() > 0) {
                            int i = in.read(tmp, 0, 1024);
                            if (i < 0)
                                break;
                            sb.append(new String(tmp, 0, i));
                        }
                        if (channel.isClosed()) {
                            sshResult.setExitStatus(channel.getExitStatus());
                            sshResult.setOutputText(sb.toString());
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (Exception ee) {
                        }
                    }
                } finally {
                    if (channel != null) {
                        channel.disconnect();
                    }
                }
            }

        } catch (JSchException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }

        return rs;
    }
}
