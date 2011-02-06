package pm.device;

import java.util.Queue;

import pm.action.Action;
import pm.event.Target;

public abstract class Device {
    protected static Queue<Action> actionQueue;
    protected MacroListener macroListener;

    public Device() {
        macroListener = new MacroListener();
        macroListener.start();
    }

    public void addAction(Action action, Target target) {
        action.setTarget(target);
        actionQueue.add(action);
    }

    public static void initialise(Queue<Action> actionQueue) {
        Device.actionQueue = actionQueue;        
    }

    public void start() {}
    public void exit() {}
}
