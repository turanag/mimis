package pm.device.javainput.extreme3d;

import pm.Action;
import pm.Button;
import pm.Macro;
import pm.Target;
import pm.device.javainput.DirectionButton;
import pm.device.javainput.JavaInputDevice;
import pm.exception.MacroException;
import pm.exception.device.DeviceInitialiseException;
import pm.exception.event.UnknownButtonException;
import pm.exception.event.UnknownDirectionException;
import pm.macro.event.Hold;
import pm.macro.event.Press;
import pm.macro.event.Release;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public class Extreme3DDevice extends JavaInputDevice {
    protected static final String NAME = "Logitech Extreme 3D";

    public void initialise() throws DeviceInitialiseException {
        super.initialise(NAME);
        try {
            add(
                new Press(Extreme3DButton.TWELVE),
                Action.TEST.setTarget(Target.APPLICATION));
            add(
                new Macro(
                    new Hold(Extreme3DButton.ONE),
                    new Press(Extreme3DButton.TWO),
                    new Press(Extreme3DButton.ELEVEN),
                    new Release(Extreme3DButton.ONE)),
                Action.EXIT.setTarget(Target.MAIN));
        } catch (MacroException e) {
            e.printStackTrace();
        }
    }

    protected Button getButton(JXInputButtonEvent event) throws UnknownButtonException {
        return Extreme3DButton.create(event);
    }

    protected Button getButton(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return DirectionButton.create(event);
    }
}
