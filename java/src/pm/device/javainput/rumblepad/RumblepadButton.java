package pm.device.javainput.rumblepad;

import de.hardcode.jxinput.event.JXInputButtonEvent;
import pm.Button;
import pm.device.javainput.JavaInputButton;
import pm.exception.event.UnknownButtonException;

public enum RumblepadButton implements Button {
    ONE    (0),
    TWO    (1),
    THREE  (2),
    FOUR   (3),
    FIVE   (4),
    SIX    (5),
    SEVEN  (6),
    EIGHT  (7),
    NINE   (8),
    TEN    (9);

    protected int code;

    private RumblepadButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static JavaInputButton create(int code) throws UnknownButtonException {
        for (RumblepadButton button : RumblepadButton.values()) {
            if (button.getCode() == code) {
                return new JavaInputButton(code + 1);
            }
        }
        throw new UnknownButtonException();
    }

    public static JavaInputButton create(JXInputButtonEvent event) throws UnknownButtonException {
        String name = event.getButton().getName();
        String button = name.replaceFirst("Button ", "");
        int code = Integer.valueOf(button);
        //System.out.println(name + " " + button + " " + code);
        return create(code);
    }

    public String toString() {
        return String.valueOf(getCode());
    }
}