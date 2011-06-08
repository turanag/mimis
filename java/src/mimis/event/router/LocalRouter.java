package mimis.event.router;

import mimis.Event;
import mimis.event.Task;
import mimis.event.EventListener;
import mimis.event.EventRouter;
import mimis.feedback.TextFeedback;
import mimis.value.Target;

public class LocalRouter extends EventRouter {
    public void event(Event event) {
        Target target = event.getTarget();
        switch (target) {
            case APPLICATION:
                if (application != null) {
                    application.add(event);
                }
                if (event instanceof Task) {
                    Task task = (Task) event;
                    add(new TextFeedback(String.format("Action (%s): %s", task.getSignal(), task.getAction())));
                }
                break;
            default:
                for (EventListener eventListener : eventListenerList) {
                    if (event.compatible(eventListener)) {
                        eventListener.add(event);
                    }
                }
        }
    }
}
