package mimis;

import mimis.event.EventHandler;
import mimis.manager.Titled;

public abstract class Application extends EventHandler implements Titled, Exitable {
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
        if (active()) {
            deactivate();
        }
        stop();
    }
}