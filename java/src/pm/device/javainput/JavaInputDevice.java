package pm.device.javainput;

import de.hardcode.jxinput.JXInputDevice;
import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

import pm.Button;
import pm.device.Device;
import pm.exception.DeviceException;
import pm.exception.EventException;
import pm.exception.device.JavaInputDeviceNotFoundException;
import pm.macro.event.Press;
import pm.macro.event.Release;

public abstract class JavaInputDevice extends Device {
    protected JavaInputListener javaInputListener;
    protected Button previousDirectionalButton;

    protected JavaInputDevice(String name) throws DeviceException {
        super();
        javaInputListener = new JavaInputListener(this, getDevice(name));
   }

    public void start() {
        javaInputListener.start();
    }

    public void exit() {
        javaInputListener.exit();
    }

    public static JXInputDevice getDevice(String name) throws DeviceException {
        int numberOfDevices = JXInputManager.getNumberOfDevices();
        for (int i = 0; i < numberOfDevices; ++i) {
            JXInputDevice device = JXInputManager.getJXInputDevice(i);
            if (device.getName().startsWith(name)) {
                return device;
            }
        }
        throw new JavaInputDeviceNotFoundException();
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
            System.out.println(">" + new Release(button));
            add(new Release(button));
            System.out.println("erna");
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
}
