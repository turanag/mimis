package pm.event;

import pm.Event;
import pm.value.Action;
import pm.value.Target;

public class Task extends Event {
    protected Action action;

    public Task(Target target, Action action) {
        super(target);
        this.action = action;
    }

    public Task(Action action) {
        this(Target.SELF, action);
    }

    public Action getAction() {
        return action;
    }
}
