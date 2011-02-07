package pm.device.javainput.extreme3d;

import de.hardcode.jxinput.event.JXInputDirectionalEvent;
import pm.Button;
import pm.exception.event.UnknownDirectionException;

public enum Extreme3DDirection implements Button {
    NORTH     (0),
    NORTHEAST (45),
    EAST      (90),
    SOUTHEAST (135),
    SOUTH     (180),
    SOUTHWEST (225),
    WEST      (270),
    NORTHWEST (315);

    protected int code;

    private Extreme3DDirection(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Extreme3DDirection create(int angle) throws UnknownDirectionException  {
        for (Extreme3DDirection button : Extreme3DDirection.values()) {
            if (button.getCode() == angle) {
                return button;
            }
        }
        throw new UnknownDirectionException();
    }

    public static Extreme3DDirection create(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return create(event.getDirectional().getDirection() / 100);      
    }
}