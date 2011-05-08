package pm.event;

import pm.Event;
import pm.value.Action;
import pm.value.Target;

public class Task extends Event {
    protected Action action;

    public Task(Action action, Target target) {
        super(target);
        this.action = action;
    }

    public Task(Action action) {
        this(action, Target.SELF);
    }

    public Action getAction() {
        return action;
    }
}
