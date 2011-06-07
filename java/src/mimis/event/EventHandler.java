package mimis.event;

import mimis.Event;
import mimis.value.Action;

public abstract class EventHandler extends EventListener {
    protected static EventRouter eventRouter;

    public static void initialise(EventRouter eventRouter) {
        EventHandler.eventRouter = eventRouter;
    }

    public final void event(Event event) {
        if (event instanceof Feedback) {
            feedback((Feedback) event);
        } else if (event instanceof Task) {
            task((Task) event);
        }
    }

    protected void feedback(Feedback feedback) {}

    protected final void task(Task task) {
        Action action = task.getAction();
        switch (task.getSignal()) {
            case BEGIN:
                begin(action);
                break;
            case END:
                end(action);
                break;
        }
    }

    protected void begin(Action action) {}
    protected void end(Action action) {}
}