package mimis.device.javainput.rumblepad;

import mimis.Button;
import mimis.exception.button.UnknownButtonException;
import de.hardcode.jxinput.event.JXInputButtonEvent;

public enum RumblepadButton implements Button {
    ONE    ("SelectButton 0"),
    TWO    ("SelectButton 1"),
    THREE  ("SelectButton 2"),
    FOUR   ("SelectButton 3"),
    FIVE   ("SelectButton 4"),
    SIX    ("SelectButton 5"),
    SEVEN  ("SelectButton 6"),
    EIGHT  ("SelectButton 7"),
    NINE   ("SelectButton 8"),
    TEN    ("SelectButton 9");

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