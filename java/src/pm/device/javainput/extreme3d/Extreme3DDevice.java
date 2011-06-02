package pm.device.javainput.extreme3d;

import pm.Button;
import pm.Macro;
import pm.device.javainput.DirectionButton;
import pm.device.javainput.JavaInputDevice;
import pm.event.Task;
import pm.exception.MacroException;
import pm.exception.button.UnknownButtonException;
import pm.exception.button.UnknownDirectionException;
import pm.macro.state.Hold;
import pm.macro.state.Press;
import pm.macro.state.Release;
import pm.value.Action;
import pm.value.Target;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public class Extreme3DDevice extends JavaInputDevice {
    protected static final String NAME = "Logitech Extreme 3D";

    public Extreme3DDevice() {
        super(NAME);
    }

    public void initialise() {
        try {
            add(
                new Press(Extreme3DButton.TWELVE),
                new Task(Target.APPLICATION, Action.TEST));
            add(
                new Macro(
                    new Hold(Extreme3DButton.ONE),
                    new Press(Extreme3DButton.TWO),
                    new Press(Extreme3DButton.ELEVEN),
                    new Release(Extreme3DButton.ONE)),
                new Task(Target.MIMIS, Action.EXIT));
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
