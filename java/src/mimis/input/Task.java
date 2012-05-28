package mimis.input;

import mimis.value.Action;import mimis.input.Task;

import mimis.value.Signal;
import mimis.value.Target;

public class Task implements Input {
    protected static final long serialVersionUID = 1L;

    public static final Target TARGET = Target.ALL;
    public static final Signal SIGNAL = Signal.NONE;

    protected Target target;
    protected Action action;
    protected Signal signal;

    public Task(Action action) {
        this(action, TARGET);
    }

    public Task(Action action, Target target) {
        this(action, target, SIGNAL);
    }

    public Task(Action action, Target target, Signal signal) {
        this.target = target;
        this.action = action;
        this.signal = signal;
    }

    public Target getTarget() {
        return target;
    }

    public Action getAction() {
        return action;
    }

    public Signal getSignal() {
        return signal;
    }

    public Task setSignal(Signal signal) {
        return new Task(action, target, signal);
    }
}
