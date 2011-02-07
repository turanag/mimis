package pm.device;

import java.util.Queue;

import pm.action.Actions;
import pm.action.Targets;

public abstract class Device {
    protected static Queue<Actions> actionQueue;
    protected MacroListener macroListener;

    public Device() {
        macroListener = new MacroListener();
        macroListener.start();
    }

    public void addAction(Actions action, Targets target) {
        action.setTarget(target);
        actionQueue.add(action);
    }

    public static void initialise(Queue<Actions> actionQueue) {
        Device.actionQueue = actionQueue;        
    }

    public void start() {}
    public void exit() {}
}
