package pm;

import pm.event.EventListener;
import pm.value.Target;

public class Event  {
    protected static final long serialVersionUID = 1L;

    protected Target target;

    public Event(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }

    public boolean compatible(EventListener eventListener) {
        switch (target) {
            case ALL:
                return true;
            case MIMIS:
                return eventListener instanceof Mimis;
            case DEVICES:
                return eventListener instanceof Device;
            case APPLICATIONS:
                return eventListener instanceof Application;
            default:
                return false;
        }
    }
}
