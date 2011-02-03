package pm.event;

import pm.action.Action;

public class Event {
    protected Type type;
    protected Action action;

    public Event(Type type, Action action) {
        this.type = type;
        this.action = action;
    }

    public Type getType() {
        return type;
    }

    public Action getAction() {
        return action;
    }
}
