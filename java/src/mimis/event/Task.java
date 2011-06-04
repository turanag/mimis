package mimis.event;

import mimis.Event;
import mimis.value.Action;
import mimis.value.Signal;
import mimis.value.Target;

public class Task extends Event {
    public static Target TARGET = Target.SELF;
    public static Signal SIGNAL = Signal.END;

    protected Action action;
    protected Signal signal;

    public Task(Action action) {
        this(TARGET, action);
    }

    public Task(Target target, Action action) {
        this(target, action, SIGNAL);
    }

    public Task(Target target, Action action, Signal signal) {
        super(target);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public Signal getSignal() {
        return signal;
    }
}
