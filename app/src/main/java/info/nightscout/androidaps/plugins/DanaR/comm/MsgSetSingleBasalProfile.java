package info.nightscout.androidaps.plugins.DanaR.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.nightscout.androidaps.Config;

public class MsgSetSingleBasalProfile extends MessageBase {
    private static Logger log = LoggerFactory.getLogger(MsgSetSingleBasalProfile.class);

    public MsgSetSingleBasalProfile() {
        SetCommand(0x3302);
    }

    // index 0-3
    public MsgSetSingleBasalProfile(double[] values) {
        this();
        for (Integer i = 0; i < 24; i++) {
            AddParamInt((int) (values[i] * 100));
        }
        if (Config.logDanaMessageDetail)
            log.debug("Set basal profile");
    }

    @Override
    public void handleMessage(byte[] bytes) {
        int result = intFromBuff(bytes, 0, 1);
        if (result != 1) {
            failed = true;
            log.debug("Set basal profile result: " + result + " FAILED!!!");
        } else {
            if (Config.logDanaMessageDetail)
                log.debug("Set basal profile result: " + result);
        }
    }


}
