package mimis.device.lirc.button;

import mimis.device.lirc.LircButton;
import mimis.exception.button.UnknownButtonException;

public enum PhiliphsRCLE011Button implements LircButton {
    POWER ("Standby"),
    RED ("Red"),
    GREEN ("Green"),
    YELLOW ("Yellow"),
    BLUE ("Blue"),
    TUNE ("Tune"),
    RADIO ("Radio"),
    SQUARE ("Square"),
    MENU ("Menu"),
    TEXT ("Text"),
    UP ("Up"),
    DOWN ("Down"),
    LEFT ("Left"),
    RIGHT ("Right"),
    VOLUME_UP ("Volume+"),
    VOLUME_DOWN ("Volume-");
    /*Mute,
    Program+,
    Program-,
    1,
    2,
    3,
    4,
    5,
    6
    7,
    8,
    9,
    0,
    Clock,
    Out,
    i+,
    screenup,
    screendown,
    question;*/

    public static final String NAME = "Philips_RCLE011";

    protected String code;
    
    private PhiliphsRCLE011Button(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PhiliphsRCLE011Button create(String code) throws UnknownButtonException {
        for (PhiliphsRCLE011Button button : PhiliphsRCLE011Button.values()) {
            if (button.getCode().equals(code)) {
                return button;
            }
        }
        throw new UnknownButtonException();
    }
}
