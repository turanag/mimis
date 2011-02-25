package pm;

import pm.value.Action;
import pm.value.Target;

public class Task {
    protected Action action;
    protected Target target;

    public Task(Action action, Target target) {
        this.action = action;
        this.target = target;
    }
    
    public Task(Action action) {
        this(action, Target.SELF);
    }

    public Action getAction() {
        return action;
    }

    public Target getTarget() {
        return target;
    }
}
