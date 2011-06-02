package mimis.device.lirc.button;

import mimis.device.lirc.LircButton;

public enum DenonRC176 implements LircButton {
    ;
    
    
    
    public static final String NAME = "DENON_RC-176";

    protected String code;

    private DenonRC176(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
