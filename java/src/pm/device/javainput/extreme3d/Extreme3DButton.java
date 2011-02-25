package pm.device.javainput.extreme3d;

import de.hardcode.jxinput.event.JXInputButtonEvent;
import pm.Button;
import pm.exception.button.UnknownButtonException;

public enum Extreme3DButton implements Button {
    ONE    ("Button 0"),
    TWO    ("Button 1"),
    THREE  ("Button 2"),
    FOUR   ("Button 3"),
    FIVE   ("Button 4"),
    SIX    ("Button 5"),
    SEVEN  ("Button 6"),
    EIGHT  ("Button 7"),
    NINE   ("Button 8"),
    TEN    ("Button 9"),
    ELEVEN ("Button 10"),
    TWELVE ("Button 11");

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