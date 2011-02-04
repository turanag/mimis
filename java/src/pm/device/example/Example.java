package pm.device.example;

import java.util.Queue;

import pm.action.Action;
import pm.device.Device;
import pm.event.Target;

public class Example extends Device {
    public Example(Queue<Action> actionQueue) {
        super(actionQueue);
    }

    public void initialise() {
        addAction(Action.START, Target.APPLICATION);
        addAction(Action.TEST, Target.APPLICATION);
        addAction(Action.EXIT, Target.MAIN);
    }
}
