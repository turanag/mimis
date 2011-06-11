package mimis.device.javainput.extreme3d;

import mimis.Button;
import mimis.device.javainput.DirectionButton;
import mimis.device.javainput.JavaInputDevice;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.button.UnknownDirectionException;
import mimis.exception.worker.ActivateException;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public class Extreme3DDevice extends JavaInputDevice {
    protected static final String TITLE = "Extreme 3D";
    protected static final String NAME = "Logitech Extreme 3D";

    protected static Extreme3DEventMapCycle eventMapCycle;

    public Extreme3DDevice() {
        super(TITLE, NAME);
        eventMapCycle = new Extreme3DEventMapCycle();
    }

    public void activate() throws ActivateException {
        super.activate();
        add(eventMapCycle.mimis);
        add(eventMapCycle.player);
        add(eventMapCycle.like);
    }

    protected Button getButton(JXInputButtonEvent event) throws UnknownButtonException {
        return Extreme3DButton.create(event);
    }

    protected Button getButton(JXInputDirectionalEvent event) throws UnknownDirectionException {
        return DirectionButton.create(event);
    }
}
