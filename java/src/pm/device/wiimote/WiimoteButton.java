package pm.device.wiimote;

import pm.Button;
import pm.exception.event.UnknownButtonException;

public enum WiimoteButton implements Button {
    TWO   (0x0001),
    ONE   (0x0002),
    B     (0x0004),
    A     (0x0008),
    MINUS (0x0010),
    HOME  (0x0080),
    LEFT  (0x0100),
    RIGHT (0x0200),
    DOWN  (0x0400),
    UP    (0x0800),
    PLUS  (0x1000),
    ALL   (0x1F9F);

    protected int code;

    private WiimoteButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static WiimoteButton create(int code) throws UnknownButtonException {
        for (WiimoteButton button : WiimoteButton.values()) {
            if (button.getCode() == code) {
                return button;
            }
        }
        throw new UnknownButtonException();
    }
}
