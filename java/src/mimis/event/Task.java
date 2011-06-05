package mimis.event;

import mimis.Event;
import mimis.value.Action;
import mimis.value.Signal;
import mimis.value.Target;

public class Task extends Event {
    public static final Target TARGET = Target.SELF;

    protected Action action;
    protected Signal signal;

    public Task(Action action) {
        this(TARGET, action);
    }

    public Task(Target target, Action action) {
       super(target);
       this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }
}
