package com.tts.app.configcenter.ssh.feature;

import com.tts.app.configcenter.service.ssh.CmdStatus;

public interface CmdFeature {
    String getName();
    boolean check();
    CmdStatus install();
    CmdStatus uninstall();
}
