package pm.device.javainput.rumblepad;

import pm.Action;
import pm.Target;
import pm.device.javainput.JavaInputDevice;
import pm.exception.DeviceException;
import pm.exception.MacroException;
import pm.macro.event.Press;

public class RumblepadDevice extends JavaInputDevice {

    protected static final String NAME = "Logitech RumblePad 2 USB";

    public RumblepadDevice() throws DeviceException {
        super(NAME);
    }

    public void start() {
        super.start();
        try {
            /*add(
                new Press(RumblepadButton.ONE),
                Action.PLAY.setTarget(Target.APPLICATION));
            add(
                new Press(RumblepadButton.TWO),
                Action.PAUSE.setTarget(Target.APPLICATION));
            add(
                new Press(RumblepadButton.THREE),
                Action.RESUME.setTarget(Target.APPLICATION));*/
            add(
                new Press(RumblepadButton.FOUR),
                Action.EXIT.setTarget(Target.MAIN));
        } catch (MacroException e) {
            e.printStackTrace();
        }
    }  
}
