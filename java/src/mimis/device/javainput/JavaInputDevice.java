package mimis.device.javainput;

import mimis.Button;
import mimis.exception.ButtonException;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.button.UnknownDirectionException;
import mimis.exception.device.DeviceNotFoundException;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.input.state.Press;
import mimis.input.state.Release;
import mimis.worker.Component;
import de.hardcode.jxinput.JXInputDevice;
import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

public abstract class JavaInputDevice extends Component {
    protected String name;

    public JavaInputDevice(String title, String name) {
        super(title);
        this.name = name;
    }

    protected JavaInputListener javaInputListener;
    protected Button previousDirectionalButton;

    protected void activate() throws ActivateException {
        super.activate();
        try {
            JXInputDevice jxinputDevice = getDevice(name);
            log.debug(jxinputDevice);
            javaInputListener = new JavaInputListener(this, jxinputDevice);
        } catch (DeviceNotFoundException e) {
            active = false;
            throw new ActivateException();
        }
        javaInputListener.start();
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        javaInputListener.stop();
    }

    public void processEvent(JXInputAxisEvent event) {}

    public void processEvent(JXInputButtonEvent event) throws ButtonException {
        Button button = getButton(event);
        if (event.getButton().getState()) {
            add(new Press(button));
        } else {
            add(new Release(button));
        }
    }

    public void processEvent(JXInputDirectionalEvent event) throws UnknownDirectionException {
        Button button = getButton(event);
        if (event.getDirectional().isCentered()) {
            if (previousDirectionalButton != null) {
                add(new Release(previousDirectionalButton));
            }
        } else {
            add(new Press(button));
            previousDirectionalButton = button;
        }
    }

    protected abstract Button getButton(JXInputButtonEvent event) throws UnknownButtonException;
    protected abstract Button getButton(JXInputDirectionalEvent event) throws UnknownDirectionException;

    public static JXInputDevice getDevice(String name) throws DeviceNotFoundException {
        int numberOfDevices = JXInputManager.getNumberOfDevices();
        for (int i = 0; i < numberOfDevices; ++i) {
            JXInputDevice device = JXInputManager.getJXInputDevice(i);
            if (device.getName().startsWith(name)) {
                return device;
            }
        }
        throw new DeviceNotFoundException();
    }
}
