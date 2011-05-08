package pm;

import java.io.Serializable;

import pm.event.EventListener;
import pm.value.Target;

public class Event implements Serializable {
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
            case MANAGER:
                return eventListener instanceof Manager;
            case DEVICES:
                return eventListener instanceof Device;
            case APPLICATIONS:
                return eventListener instanceof Application;
            default:
                return false;
        }
    }
}
