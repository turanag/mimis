package mimis.event;

import mimis.Event;
import mimis.value.Action;
import mimis.value.Signal;
import mimis.value.Target;

public class Task extends Event {
    protected static final long serialVersionUID = 1L;

    public static final Target TARGET = Target.SELF;

    protected Action action;
    protected Signal signal;

    public Task(Action action) {
        this(TARGET, action);
    }

    public Task(Target target, Action action) {
        this(target, action, null);
    }

    public Task(Target target, Action action, Signal signal) {
        super(target);
        this.action = action;
        this.signal = signal;
    }

    public Action getAction() {
        return action;
    }

    public Signal getSignal() {
        return signal;
    }

    public Task setSignal(Signal signal) {
        return new Task(target, action, signal);
    }
}
