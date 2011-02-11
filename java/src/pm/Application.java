package pm;

import pm.action.ActionListener;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;

public abstract class Application extends ActionListener {  
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
}