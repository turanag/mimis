package pm;

import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.task.TaskListener;

public abstract class Application extends TaskListener {  
    public void run() {
        try {
            initialise();
            super.run();
        } catch (ApplicationInitialiseException e) {
            e.printStackTrace();
        }
    }

    public void initialise() throws ApplicationInitialiseException {}

    public void exit() throws ApplicationExitException {
        stop();
    }

    protected void task(Task task) {
        action(task.getAction());
    }

    protected abstract void action(Action action);
}