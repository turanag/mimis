package mimis;

import mimis.event.EventHandler;
import mimis.event.Task;
import mimis.exception.WorkerException;
import mimis.exception.worker.DeactivateException;
import mimis.manager.Titled;
import mimis.value.Action;

public abstract class Application extends EventHandler implements Titled, Exitable {
    protected String title;
    protected boolean active;

    public Application(String title) {
        this.title = title;
        //initialise();
    }

    public String title() {
        return title;
    }

    public void add(Event event) {
        if (event instanceof Task) {
            Task task = (Task) event;
            Action action = task.getAction();
            switch (action) {
                case ACTIVATE:
                    try {
                        if (active()) {
                            deactivate();
                        } else {
                            activate();
                        }
                    } catch (WorkerException e) {
                        log.error(e);
                    }
                    return;
            }
        }
        super.event(event);
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