package pm;

import pm.event.EventHandler;
import pm.selector.Selectable;

public abstract class Application extends EventHandler implements Selectable {
    protected String title;
    protected boolean active;

    public Application(String title) {
        this.title = title;
        active = false;
    }

    public String title() {
        return title;
    }

    public void exit() {
        deactivate();
        stop();
    }
}