package mimis.device.javainput;

import mimis.Button;
import mimis.exception.button.UnknownDirectionException;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public enum DirectionButton implements Button {
    NORTH     (0),
    NORTHEAST (45),
    EAST      (90),
    SOUTHEAST (135),
    SOUTH     (180),
    SOUTHWEST (225),
    WEST      (270),
    NORTHWEST (315);

    protected int code;

    private DirectionButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DirectionButton create(int angle) throws UnknownDirectionException  {
        for (DirectionButton button : DirectionButton.values()) {
            if (button.getCode() == angle) {
                return button;
            }
        }
        throw new UnknownDirectionException();
    }

    public static DirectionButton create(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return create(event.getDirectional().getDirection() / 100);      
    }
}
