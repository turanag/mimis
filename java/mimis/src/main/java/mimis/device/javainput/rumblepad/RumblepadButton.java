package mimis.device.javainput.rumblepad;

import mimis.exception.button.UnknownButtonException;
import mimis.input.Button;
import de.hardcode.jxinput.event.JXInputButtonEvent;

public enum RumblepadButton implements Button {
    ONE    ("Button 0"),
    TWO    ("Button 1"),
    THREE  ("Button 2"),
    FOUR   ("Button 3"),
    FIVE   ("Button 4"),
    SIX    ("Button 5"),
    SEVEN  ("Button 6"),
    EIGHT  ("Button 7"),
    NINE   ("Button 8"),
    TEN    ("Button 9");

    protected String code;

    private RumblepadButton(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static RumblepadButton create(String code) throws UnknownButtonException {
        for (RumblepadButton button : RumblepadButton.values()) {
            if (button.getCode().equals(code)) {
                return button;
            }
        }
        throw new UnknownButtonException();
    }

    public static RumblepadButton create(JXInputButtonEvent event) throws UnknownButtonException {
        return create(event.getButton().getName());      
    }
}