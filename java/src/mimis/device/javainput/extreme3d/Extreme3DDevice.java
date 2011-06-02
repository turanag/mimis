package mimis.device.javainput.extreme3d;

import mimis.Button;
import mimis.Macro;
import mimis.device.javainput.DirectionButton;
import mimis.device.javainput.JavaInputDevice;
import mimis.event.Task;
import mimis.exception.MacroException;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.button.UnknownDirectionException;
import mimis.macro.state.Hold;
import mimis.macro.state.Press;
import mimis.macro.state.Release;
import mimis.value.Action;
import mimis.value.Target;
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
