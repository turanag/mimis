package pm.action;

import java.util.Queue;

import pm.Action;

public class ActionProvider {
    protected static Queue<Action> actionQueue;

    public static void initialise(Queue<Action> actionQueue) {
        ActionProvider.actionQueue = actionQueue;        
    }

    public static void add(Action action) {
        actionQueue.add(action);
    }
}
