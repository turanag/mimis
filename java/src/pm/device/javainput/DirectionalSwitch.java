package pm.device.javainput;

import pm.Button;
import pm.exception.event.UnknownDirectionException;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public enum DirectionalSwitch implements Button {
    NORTH     (0),
    NORTHEAST (45),
    EAST      (90),
    SOUTHEAST (135),
    SOUTH     (180),
    SOUTHWEST (225),
    WEST      (270),
    NORTHWEST (315);

    protected int code;

    private DirectionalSwitch(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DirectionalSwitch create(int angle) throws UnknownDirectionException  {
        for (DirectionalSwitch button : DirectionalSwitch.values()) {
            if (button.getCode() == angle) {
                return button;
            }
        }
        throw new UnknownDirectionException();
    }

    public static DirectionalSwitch create(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return create(event.getDirectional().getDirection() / 100);      
    }
}
