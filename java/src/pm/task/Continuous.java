package pm.task;

import pm.event.Task;
import pm.value.Action;
import pm.value.Target;

public class Continuous extends Task {
    protected static final int SLEEP = 0;
    
    protected int sleep;
    protected int iteration;
    protected boolean stop;

    public Continuous(Action action, Target target, int sleep) {
        super(action, target);
        this.sleep = sleep;
        reset();
    }

    public Continuous(Action action, Target target) {
        this(action, target, SLEEP);
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
