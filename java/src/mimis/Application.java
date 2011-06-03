package mimis;

import mimis.event.EventHandler;
import mimis.exception.worker.DeactivateException;
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

    public void stop() {
        if (active()) {
            try {
                deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        }
        super.stop();
    }
}