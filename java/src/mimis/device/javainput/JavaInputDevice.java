package mimis.device.javainput;

import mimis.Button;
import mimis.Device;
import mimis.exception.ButtonException;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.button.UnknownDirectionException;
import mimis.exception.device.DeviceInitialiseException;
import mimis.exception.device.DeviceNotFoundException;
import mimis.macro.state.Press;
import mimis.macro.state.Release;
import de.hardcode.jxinput.JXInputDevice;
import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;


public abstract class JavaInputDevice extends Device {
    public JavaInputDevice(String title) {
        super(title);
    }

    protected JavaInputListener javaInputListener;
    protected Button previousDirectionalButton;

    public void activate(String name) throws DeviceInitialiseException {
        try {
            javaInputListener = new JavaInputListener(this, getDevice(name));
            javaInputListener.start();
        } catch (DeviceNotFoundException e) {
            throw new DeviceInitialiseException();
        }        
    }

    public void deactivate() {
        javaInputListener.exit();
    }

    public void processEvent(JXInputAxisEvent event) {
        //System.out.println(event);
    }

    public void processEvent(JXInputButtonEvent event) throws ButtonException {
        Button button = getButton(event);
        if (event.getButton().getState()) {
            System.out.println("Press: " + button);
            add(new Press(button));
        } else {
            System.out.println("Release: " + button);
            add(new Release(button));
        }
    }

    public void processEvent(JXInputDirectionalEvent event) throws UnknownDirectionException {
        Button button = getButton(event);
        if (event.getDirectional().isCentered()) {
            if (previousDirectionalButton != null) {
                System.out.println("Release: " + previousDirectionalButton);
                add(new Release(previousDirectionalButton));
            }
        } else {
            System.out.println("Press: " + button);
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