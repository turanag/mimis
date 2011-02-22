package pm.task;

import pm.value.Action;
import pm.value.Target;

public class Dynamic extends Continuous {
    protected static final int RATE = 10;
    
    protected int rate;

    public Dynamic(Action action, Target target, int sleep, int rate) {
        super(action, target, sleep);
        this.rate = rate;
    }
    
    public Dynamic(Action action, Target target, int sleep) {
        super(action, target, sleep);
        this.rate = RATE;
    }
    
    public int getSleep() {
        return sleep + rate * iteration;
    }
}