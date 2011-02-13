package pm;

import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.task.Continuous;
import pm.task.TaskListener;

public abstract class Application extends TaskListener {  
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

    protected void task(Task task) {
        Action action = task.getAction();
        if (task instanceof Continuous) {
            Continuous continuous = (Continuous) task;
            do {
                action(action);
                continuous.nextIteration();
                sleep(continuous.getSleep());
            } while (run && !continuous.getStop());
            continuous.reset();
        } else {
            action(action);
        }        
    }

    protected abstract void action(Action action);
}