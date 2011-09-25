package mimis.device.javainput.rumblepad;

import mimis.Button;
import mimis.device.javainput.DirectionButton;
import mimis.device.javainput.JavaInputDevice;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.button.UnknownDirectionException;
import mimis.exception.worker.ActivateException;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public class RumblepadDevice extends JavaInputDevice {
    protected static final String TITLE = "RumblePad";
    protected static final String NAME = "Logitech RumblePad 2 USB";

    protected static RumblepadEventMapCycle eventMapCycle;

    public RumblepadDevice() {
        super(TITLE, NAME);
        eventMapCycle = new RumblepadEventMapCycle(); 
    }

    protected void activate() throws ActivateException {
        super.activate();
        add(eventMapCycle.mimis);
        add(eventMapCycle.player);
        add(eventMapCycle.like);
    }

    protected Button getButton(JXInputButtonEvent event) throws UnknownButtonException {
        return RumblepadButton.create(event);
    }

    protected Button getButton(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return DirectionButton.create(event);
    }
}
