package pm.device.javainput.rumblepad;

import de.hardcode.jxinput.event.JXInputDirectionalEvent;
import pm.Button;
import pm.exception.event.UnknownDirectionException;

public enum RumblepadDirection implements Button {
    NORTH     (0),
    NORTHEAST (45),
    EAST      (90),
    SOUTHEAST (135),
    SOUTH     (180),
    SOUTHWEST (225),
    WEST      (270),
    NORTHWEST (315);

    protected int code;

    private RumblepadDirection(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static RumblepadDirection create(int angle) throws UnknownDirectionException  {
        for (RumblepadDirection button : RumblepadDirection.values()) {
            if (button.getCode() == angle) {
                return button;
            }
        }
        throw new UnknownDirectionException();
    }

    public static RumblepadDirection create(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return create(event.getDirectional().getDirection() / 100);      
    }
}