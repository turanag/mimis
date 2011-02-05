package pm.device;

import java.util.Queue;

import de.hardcode.jxinput.JXInputDevice;

import pm.action.Action;
import pm.exception.ServiceJavaInputException;
import pm.service.javainput.JavaInputService;

public abstract class JavaInputDevice extends Device {
    
    public static JavaInputService jxinputService;
    
    protected JavaInputDevice(Queue<Action> actionQueue, String name) throws ServiceJavaInputException {
        super(actionQueue);
        if (jxinputService == null) {
            throw new ServiceJavaInputException();
        }
        JXInputDevice x = jxinputService.getDevice(name);
        System.out.printf("Initialized: %s\n", x.getName());
    }

    public void initialise() {
        
    }
}
