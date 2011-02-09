package pm.device.javainput.rumblepad;

import pm.Action;
import pm.Button;
import pm.Macro;
import pm.Target;
import pm.device.javainput.EightfoldDirection;
import pm.device.javainput.JavaInputDevice;
import pm.exception.DeviceException;
import pm.exception.MacroException;
import pm.exception.event.UnknownButtonException;
import pm.exception.event.UnknownDirectionException;
import pm.macro.event.Press;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public class RumblepadDevice extends JavaInputDevice {

    protected static final String NAME = "Logitech RumblePad 2 USB";

    public RumblepadDevice() throws DeviceException {
        super(NAME);
    }

    public void start() {
        super.start();
        try {
            add(
                new Press(RumblepadButton.ONE),
                Action.PLAY.setTarget(Target.APPLICATION));
            add(
                new Press(RumblepadButton.TWO),
                Action.PAUSE.setTarget(Target.APPLICATION));
            add(
                new Press(RumblepadButton.THREE),
                Action.RESUME.setTarget(Target.APPLICATION));
            add(
                new Macro(
                    new Press(RumblepadButton.NINE)
                ),
                Action.EXIT.setTarget(Target.MAIN));
        } catch (MacroException e) {
            e.printStackTrace();
        }
    }

    protected Button getButton(JXInputButtonEvent event) throws UnknownButtonException {
        return RumblepadButton.create(event);
    }

    protected Button getButton(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return EightfoldDirection.create(event);
    }
}
