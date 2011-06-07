package mimis.event.router;

import mimis.Event;
import mimis.event.EventListener;
import mimis.event.EventRouter;
import mimis.value.Target;

public class LocalRouter extends EventRouter {
    public void event(Event event) {
        Target target = event.getTarget();
        switch (target) {
            case APPLICATION:
                if (application != null) {
                    application.add(event);
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
