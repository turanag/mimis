package mimis;

import mimis.event.EventHandler;
import mimis.event.Task;
import mimis.event.feedback.TextFeedback;
import mimis.exception.WorkerException;
import mimis.exception.worker.DeactivateException;
import mimis.manager.Exitable;
import mimis.manager.Titled;
import mimis.value.Action;
import mimis.value.Signal;

public abstract class Application extends EventHandler implements Titled, Exitable {
    protected String title;
    protected boolean active;

    public Application(String title) {
        this.title = title;
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
                    if (task.getSignal().equals(Signal.BEGIN)) {
                        try {
                            if (active()) {
                                eventRouter.add(new TextFeedback("Deactivate application"));
                                deactivate();
                            } else {
                                eventRouter.add(new TextFeedback("Activate application"));
                                activate();
                            }
                        } catch (WorkerException e) {
                            log.error(e);
                        }
                    }
                    return;
            }
        }
        super.event(event);
    }

    public void stop() throws DeactivateException {
        super.stop();
        if (active()) {
            try {
                deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        }
    }
}