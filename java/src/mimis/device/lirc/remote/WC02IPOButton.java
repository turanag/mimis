package mimis.device.lirc.remote;

import mimis.device.lirc.LircButton;

public enum WC02IPOButton implements LircButton {
    MINUS ("MINUS"),
    PLUS ("PLUS"),
    NEXT ("NEXT"),
    PREVIOUS ("PREVIOUS"),
    PLAY ("PLAY"),
    HOLD ("HOLD");

    public static final String NAME = "WC02-IPO";

    protected String code;

    private WC02IPOButton(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    public String getName() {
        return NAME;
    }
}
