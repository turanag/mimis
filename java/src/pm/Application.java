package pm;

import pm.event.EventHandler;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.selector.Selectable;

public abstract  class Application extends EventHandler implements Selectable {
    public void initialise() throws ApplicationInitialiseException {}

    public void exit() throws ApplicationExitException {
        stop();
    }
}