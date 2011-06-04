package mimis.device.javainput.rumblepad;

import mimis.Button;
import mimis.device.javainput.DirectionButton;
import mimis.device.javainput.JavaInputDevice;
import mimis.event.Task;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.button.UnknownDirectionException;
import mimis.exception.worker.ActivateException;
import mimis.macro.state.Press;
import mimis.value.Action;
import mimis.value.Target;

import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public class RumblepadDevice extends JavaInputDevice {
    protected static final String TITLE = "RumblePad";
    protected static final String NAME = "Logitech RumblePad 2 USB";

    public RumblepadDevice() {
        super(TITLE, NAME);
    }

    public void activate() throws ActivateException {
        super.activate();
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
            new Task(Target.MIMIS, Action.NEXT));
        add(
            new Press(RumblepadButton.EIGHT),
            new Task(Target.MIMIS, Action.PREVIOUS));
        /*add(
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
            new Continuous(Action.VOLUME_UP, Target.APPLICATION, 100));*/
    }

    protected Button getButton(JXInputButtonEvent event) throws UnknownButtonException {
        return RumblepadButton.create(event);
    }

    protected Button getButton(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return DirectionButton.create(event);
    }
}
