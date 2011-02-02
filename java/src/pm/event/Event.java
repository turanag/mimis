package pm.event;

import pm.action.Action;

public class Event {
    protected Action action;
    
    public Event(Action action) {
        this.action = action;
    }
}
