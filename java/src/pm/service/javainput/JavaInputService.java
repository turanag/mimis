package pm.service.javainput;

import de.hardcode.jxinput.JXInputDevice;
import de.hardcode.jxinput.JXInputManager;

import pm.device.JavaInputDevice;
import pm.exception.ServiceJavaInputDeviceNotFoundException;
import pm.exception.ServiceJavaInputException;
import pm.service.Service;

public class JavaInputService extends Service {

    public JXInputDevice getDevice(String name) throws ServiceJavaInputException {
        int numberOfDevices = JXInputManager.getNumberOfDevices();
        for (int i = 0; i < numberOfDevices; ++i) {
            JXInputDevice device = JXInputManager.getJXInputDevice(i);
            if (device.getName().startsWith(name)) {
                return device;
            }
        }
        throw new ServiceJavaInputDeviceNotFoundException();
    }

    public static void initialize() {
        JavaInputDevice.jxinputService = new JavaInputService();        
    }

}
