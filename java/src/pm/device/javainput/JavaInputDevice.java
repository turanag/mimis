package pm.device.javainput;

import de.hardcode.jxinput.JXInputDevice;
import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

import pm.Button;
import pm.Device;
import pm.exception.EventException;
import pm.exception.device.DeviceInitialiseException;
import pm.exception.device.DeviceNotFoundException;
import pm.macro.event.Press;
import pm.macro.event.Release;

public abstract class JavaInputDevice extends Device {
    protected JavaInputListener javaInputListener;
    protected Button previousDirectionalButton;

    public void initialise(String name) throws DeviceInitialiseException {
        try {
            javaInputListener = new JavaInputListener(this, getDevice(name));
            javaInputListener.start();
        } catch (DeviceNotFoundException e) {
            throw new DeviceInitialiseException();
        }        
    }

    public void exit() {
        javaInputListener.exit();
    }

    public void processEvent(JXInputAxisEvent event) {
        //System.out.println(event);
    }

    public void processEvent(JXInputButtonEvent event) throws EventException {
        Button button = getButton(event);
        if (event.getButton().getState()) {
            System.out.println("Press: " + button);
            add(new Press(button));
        } else {
            System.out.println("Release: " + button);
            add(new Release(button));
        }
    }

    public void processEvent(JXInputDirectionalEvent event) throws EventException {
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

    protected abstract Button getButton(JXInputButtonEvent event) throws EventException;
    protected abstract Button getButton(JXInputDirectionalEvent event) throws EventException;

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
