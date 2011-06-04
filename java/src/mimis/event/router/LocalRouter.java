package mimis.event.router;

import mimis.Event;
import mimis.event.EventListener;
import mimis.event.EventRouter;
import mimis.value.Target;

public class LocalRouter extends EventRouter {
    public void event(Event event) {
        System.out.println("LocalSpreader krijgt event via event() " + event.getTarget());
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
                        log.trace(eventListener);
                        eventListener.add(event);
                    }
                }
        }
    }
}
