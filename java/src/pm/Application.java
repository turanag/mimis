package pm;

import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.task.TaskGatherer;
import pm.task.TaskListener;

public abstract class Application extends TaskListener {  
    
    public Application() {
        super();
        TaskGatherer.add(this);
    }
    
    public void run() {
        try {
            initialise();
            super.run();
        } catch (ApplicationInitialiseException e) {
            e.printStackTrace(); // Todo: dit "over" de thread heengooien / loggen?
        }
    }

    public void initialise() throws ApplicationInitialiseException {}

    public void exit() throws ApplicationExitException {
        stop();
    }
}