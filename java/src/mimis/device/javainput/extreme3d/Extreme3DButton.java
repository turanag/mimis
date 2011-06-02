package mimis.device.javainput.extreme3d;

import mimis.Button;
import mimis.exception.button.UnknownButtonException;
import de.hardcode.jxinput.event.JXInputButtonEvent;

public enum Extreme3DButton implements Button {
    ONE    ("SelectButton 0"),
    TWO    ("SelectButton 1"),
    THREE  ("SelectButton 2"),
    FOUR   ("SelectButton 3"),
    FIVE   ("SelectButton 4"),
    SIX    ("SelectButton 5"),
    SEVEN  ("SelectButton 6"),
    EIGHT  ("SelectButton 7"),
    NINE   ("SelectButton 8"),
    TEN    ("SelectButton 9"),
    ELEVEN ("SelectButton 10"),
    TWELVE ("SelectButton 11");

    protected String code;

    private Extreme3DButton(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Extreme3DButton create(String code) throws UnknownButtonException {
        for (Extreme3DButton button : Extreme3DButton.values()) {
            if (button.getCode().equals(code)) {
                return button;
            }
        }
        throw new UnknownButtonException();
    }

    public static Extreme3DButton create(JXInputButtonEvent event) throws UnknownButtonException {
        return create(event.getButton().getName());      
    }
}