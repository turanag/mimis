package mimis.device.lirc.button;

import mimis.device.lirc.LircButton;

public enum DenonRC176 implements LircButton {
    TAPE_AB ("TAPE_AB"),
    TAPE_REC ("TAPE_REC"),
    TAPE_PAUSE ("TAPE_PAUSE"),
    TAPE_STOP ("TAPE_STOP"),
    TAPE_REWIND ("TAPE_REW"),
    TAPE_FORWARD ("TAPE_FF"),
    TAPE_PREIVOUS ("TAPE_PLAYREV"),
    TAPE_NEXT ("TAPE_PLAY"),
    CD_PREVIOUS ("CD_TRACK_-"),
    CD_NEXT ("CD_TRACK_+"),
    CD_SHUFFLE ("CD_RANDOM"),
    CD_REPEAT ("CD_REPEAT"),
    CD_SKIP ("CD_SKIP"),
    CD_PAUSE ("CD_PAUSE"),
    CD_STOP ("CD_STOP"),
    CD_PLAY ("CD_PLAY"),
    AMP_TAPE2 ("AMP_TAPE2"),
    AMP_TAPE1 ("AMP_TAPE1"),
    AMP_AUX ("AMP_AUX"),
    AMP_TUNER ("AMP_TUNER"),
    AMP_CD ("AMP_CD"),
    AMP_PHONO ("AMP_PHONO"),
    AMP_VOLUME_UP ("AMP_VOL_UP"),
    AMP_VOLUME_DOWN ("AMP_VOL_DOWN"),
    AMP_POWER ("AMP_POWER"),
    AMP_MUTE ("AMP_MUTE"),
    AMP_NEXT ("TUN_CH_UP"),
    AMP_PREVIOUS ("TUN_CH_DOWN");    

    public static final String NAME = "DENON_RC-176";

    protected String code;

    private DenonRC176(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return NAME;
    }
}
