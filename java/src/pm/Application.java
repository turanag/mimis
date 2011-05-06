package pm;

import pm.event.EventListener;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;

public abstract class Application extends EventListener {  
    public void initialise() throws ApplicationInitialiseException {}

    public void exit() throws ApplicationExitException {
        stop();
    }
}