package pm.device.javainput;

import de.hardcode.jxinput.JXInputDevice;
import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

import pm.device.Device;
import pm.exception.DeviceException;
import pm.exception.EventException;
import pm.exception.device.JavaInputDeviceNotFoundException;
import pm.exception.event.UnknownDirectionException;

public abstract class JavaInputDevice extends Device {
    protected JavaInputListener javaInputListener;
    //protected JXInputDevice jxinputDevice;

    protected JavaInputDevice(String name) throws DeviceException {
        super();
        javaInputListener = new JavaInputListener(this, getDevice(name));
   }

    public void start() {
        javaInputListener.start();
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

    public void processEvent(JXInputAxisEvent event) {}
    public void processEvent(JXInputButtonEvent event) throws EventException {}
    public void processEvent(JXInputDirectionalEvent event) throws EventException {}
}
