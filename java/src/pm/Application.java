package pm;

import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.task.TaskManager;
import pm.task.TaskListener;

public abstract class Application extends TaskListener {  
    public Application() {
        super();
    }

    public void initialise() throws ApplicationInitialiseException {}

    public void exit() throws ApplicationExitException {
        stop();
    }
}