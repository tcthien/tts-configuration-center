package com.tts.app.configcenter.service.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.tts.app.configcenter.service.ssh.cmd.Command;
import com.tts.app.configcenter.service.ssh.cmd.CompositeCommand;

@Named
public class SSHCommandExecutorImpl implements SSHCommandExecutor {
    
    public SSHResult execute(String host, String userName, String password, Command cmd) throws Exception {
        return execute(host, userName, password, cmd, null);
    }

    public SSHResult execute(String host, String userName, String password, Command cmd, SSHCommandCallback callback)
            throws Exception {
        SSHResult sshResult = null;
        
        List<Command> cmds = null;
        if (cmd instanceof CompositeCommand) {
            cmds = new ArrayList<>(((CompositeCommand) cmd).getSubCommands());
        } else {
            cmds = Arrays.asList(cmd);
        }

        Session session = null;
        try {

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session = jsch.getSession(userName, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();

            for (Command sshCommand : cmds) {
                if (callback == null || (callback != null && callback.preExecute(sshCommand))) {
                    sshResult = executeCommand(session, sshCommand);
                }
                
                if (callback != null && !callback.postExecute(sshCommand)) {
                    // callback.postExecute returns false => don't execute further
                    break;
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

        return sshResult;
    }

    protected SSHResult executeCommand(Session session, Command sshCommand) throws JSchException, IOException {
        SSHResult sshResult = new SSHResultImpl();

        Channel channel = session.openChannel("exec");
        try {
            ((ChannelExec) channel).setCommand(sshCommand.getTextCommand());
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            StringBuilder sb = new StringBuilder();
            
            int time = 0;
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    sb.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    break;
                }
                time++;
                if (sshCommand.getTimeout() == time) {
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            System.out.println("Execute Command: " + sshCommand.getTextCommand() + " with result: " + sb.toString());
            sshResult.setExitStatus(channel.getExitStatus());
            sshResult.setOutputText(sb.toString());
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
        return sshResult;
    }
}
