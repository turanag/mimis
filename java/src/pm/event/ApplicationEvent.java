package pm.event;

import pm.action.Action;

public class ApplicationEvent extends Event {
    public ApplicationEvent(Action action) {
        super(action);
    }
}
