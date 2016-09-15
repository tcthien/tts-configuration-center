package com.tts.app.configcenter.service.ssh;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.inject.Named;

@Named(value="sshConsoleLog")
public class SSHConsoleLogImpl implements SSHConsoleLog {

    private Map<String, Queue<String>> consoleLogByIpAddress = new HashMap<>();

    @Override
    public void log(String ipAddress, String outputConsole) {
        Queue<String> queue = consoleLogByIpAddress.get(ipAddress);
        if (queue == null) {
            synchronized (ipAddress) {
                queue = consoleLogByIpAddress.get(ipAddress);
                if (queue == null) {
                    queue = new LinkedList<>();
                    consoleLogByIpAddress.put(ipAddress, queue);
                }
            }
        }
        synchronized (queue) {
            queue.offer(outputConsole);
        }
    }

    @Override
    public String getLog(String ipAddress) {
        Queue<String> queue = consoleLogByIpAddress.get(ipAddress);
        if (queue == null) {
            return "";
        }
        synchronized (queue) {
            StringBuilder sb = new StringBuilder();
            for (String log : queue) {
                sb.append(log);
            }
            queue.clear();
            return sb.toString();
        }
    }

}
