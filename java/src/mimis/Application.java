package mimis;

import mimis.event.EventHandler;
import mimis.event.Task;
import mimis.event.feedback.TextFeedback;
import mimis.manager.Titled;
import mimis.value.Action;
import mimis.value.Signal;

public abstract class Application extends EventHandler implements Titled {
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
                case START:
                    if (task.getSignal().equals(Signal.BEGIN)) {
                        if (active()) {
                            eventRouter.add(new TextFeedback("Stop application"));
                            stop();
                        } else {
                            eventRouter.add(new TextFeedback("Start application"));
                            start();
                        }
                    }
                    return;
            }
        }
        super.event(event);
    }
}