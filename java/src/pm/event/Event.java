package pm.event;

import pm.action.Action;

public class Event {
    protected Target type;
    protected Action action;

    public Event(Target type, Action action) {
        this.type = type;
        this.action = action;
    }

    public Target getType() {
        return type;
    }

    public Action getAction() {
        return action;
    }
}
