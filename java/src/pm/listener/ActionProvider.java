package pm.listener;

import java.util.Queue;

import pm.Action;

public class ActionProvider {
    protected static Queue<Action> actionQueue;

    public static void initialise(Queue<Action> actionQueue) {
        ActionProvider.actionQueue = actionQueue;        
    }

    public void add(Action action) {
        System.out.println(action);
        actionQueue.add(action);
    }
}
