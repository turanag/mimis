package pm.interrupt;

import pm.event.Task;
import pm.macro.state.Press;

public class Interruptible extends Task {
    protected static final int SLEEP = 0;
    
    protected boolean triggered;
    protected boolean interrupted;
    protected Press triggerPress;
    protected Task triggerTask;
    protected Press interruptPress;
    protected Task interruptTask;
    

    public Interruptible(Press triggerPress, Task triggerTask, Press interruptPress, Task interruptTask) {
        super(null, null);
        triggered = false;
        interrupted = false;
        this.triggerPress = triggerPress;
        this.triggerTask = triggerTask;
        this.interruptPress = interruptPress;
        this.interruptTask = interruptTask;
    }        
}