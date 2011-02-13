package pm.task;

import pm.Action;
import pm.Target;
import pm.Task;

public class Continuous extends Task {
    protected int sleep;
    protected int iteration;
    protected boolean stop;

    public Continuous(Action action, Target target, int sleep) {
        super(action, target);
        this.sleep = sleep;
        reset();
    }

    public Continuous(Action action, Target target) {
        this(action, target, 0);
    }

    public void nextIteration() {
        ++iteration;
    }

    public void stop() {
        stop = true;
    }

    public void reset() {
        iteration = 0;
        stop = false;
    }

    public int getSleep() {
        return sleep;
    }

    public boolean getStop() {
        return stop;
    }
}
