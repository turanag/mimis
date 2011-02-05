package pm.device.rumblepad;

import java.util.Queue;

import pm.action.Action;
import pm.device.JavaInputDevice;
import pm.exception.ServiceJavaInputException;

public class RumblepadDevice extends JavaInputDevice {

    protected static final String NAME = "Logitech RumblePad 2 USB";
    
    public RumblepadDevice(Queue<Action> actionQueue) throws ServiceJavaInputException {
        super(actionQueue, NAME);
    }
    
}
