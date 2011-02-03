package pm.event;

import pm.action.Action;

public abstract class Event {
    protected Action action;

    public Event(Action action) {
        this.action = action;
    }
    
    public Action getAction() {
        return action;
    }
}
