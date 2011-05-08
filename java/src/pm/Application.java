package pm;

import pm.event.EventHandler;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;

public abstract class Application extends EventHandler {  
    public void initialise() throws ApplicationInitialiseException {}

    public void exit() throws ApplicationExitException {
        stop();
    }
}