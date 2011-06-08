package mimis.device.javainput.extreme3d;

import mimis.Button;
import mimis.exception.button.UnknownButtonException;
import de.hardcode.jxinput.event.JXInputButtonEvent;

public enum Extreme3DButton implements Button {
    ONE    ("ManageButton 0"),
    TWO    ("ManageButton 1"),
    THREE  ("ManageButton 2"),
    FOUR   ("ManageButton 3"),
    FIVE   ("ManageButton 4"),
    SIX    ("ManageButton 5"),
    SEVEN  ("ManageButton 6"),
    EIGHT  ("ManageButton 7"),
    NINE   ("ManageButton 8"),
    TEN    ("ManageButton 9"),
    ELEVEN ("ManageButton 10"),
    TWELVE ("ManageButton 11");

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