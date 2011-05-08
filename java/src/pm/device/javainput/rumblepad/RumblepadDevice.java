package pm.device.javainput.rumblepad;

import pm.Button;
import pm.device.javainput.DirectionButton;
import pm.device.javainput.JavaInputDevice;
import pm.event.Task;
import pm.event.task.Continuous;
import pm.event.task.Dynamic;
import pm.exception.button.UnknownButtonException;
import pm.exception.button.UnknownDirectionException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.state.Hold;
import pm.macro.state.Press;
import pm.value.Action;
import pm.value.Target;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public class RumblepadDevice extends JavaInputDevice {
    protected static final String NAME = "Logitech RumblePad 2 USB";

    public void initialise() throws DeviceInitialiseException {
        super.initialise(NAME);
        add(
            new Press(RumblepadButton.ONE),
            new Task(Target.APPLICATION, Action.PLAY));
        add(
            new Press(RumblepadButton.TWO),
            new Task(Target.APPLICATION, Action.PAUSE));
        add(
            new Press(RumblepadButton.THREE),
            new Task(Target.APPLICATION, Action.RESUME));
        add(
            new Press(RumblepadButton.SIX),
            new Task(Target.APPLICATION, Action.NEXT));
        add(
            new Press(RumblepadButton.EIGHT),
            new Task(Target.APPLICATION, Action.PREVIOUS));
        add(
            new Hold(RumblepadButton.FIVE),
            new Dynamic(Action.FORWARD, Target.APPLICATION, 200, -30));
        add(
            new Hold(RumblepadButton.SEVEN),
            new Dynamic(Action.REWIND, Target.APPLICATION, 200, -30));
        add(
            new Hold(RumblepadButton.NINE),
            new Continuous(Action.VOLUME_DOWN, Target.APPLICATION, 100));
        add(
            new Hold(RumblepadButton.TEN),
            new Continuous(Action.VOLUME_UP, Target.APPLICATION, 100));
    }

    protected Button getButton(JXInputButtonEvent event) throws UnknownButtonException {
        return RumblepadButton.create(event);
    }

    protected Button getButton(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return DirectionButton.create(event);
    }
}
