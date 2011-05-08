package pm.event.spreader;

import pm.Event;
import pm.event.EventListener;
import pm.event.EventSpreader;
import pm.value.Target;

public class LocalSpreader extends EventSpreader {
    public void event(Event event) {
        System.out.println("localspread");
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
