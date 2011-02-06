package pm.device.javainput;

import pm.exception.ServiceJavaInputException;

public class RumblepadDevice extends JavaInputDevice {

    protected static final String NAME = "Logitech RumblePad 2 USB";

    public RumblepadDevice() throws ServiceJavaInputException {
        super(NAME);
    }    
}
