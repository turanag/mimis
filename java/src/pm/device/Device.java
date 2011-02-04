package pm.device;

import java.util.Queue;

import pm.action.Action;
import pm.event.Target;


public abstract class Device {
    protected Queue<Action> actionQueue;

    public Device(Queue<Action> actionQueue) {
        this.actionQueue = actionQueue;
    }

    public void addAction(Action action, Target target) {
        action.setTarget(target);
        actionQueue.add(action);
    }

    public abstract void initialise();
}
