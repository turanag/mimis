package pm.device.javainput.rumblepad;

import pm.device.javainput.JavaInputDevice;
import pm.exception.DeviceException;

public class RumblepadDevice extends JavaInputDevice {

    protected static final String NAME = "Logitech RumblePad 2 USB";

    public RumblepadDevice() throws DeviceException {
        super(NAME);
    }    
}
