package pm.event.router;

import pm.Event;
import pm.event.EventListener;
import pm.event.EventRouter;
import pm.value.Target;

public class LocalRouter extends EventRouter {
    public void event(Event event) {
        System.out.println("LocalSpreader krijgt event via evet()");
        System.out.println(application);
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
