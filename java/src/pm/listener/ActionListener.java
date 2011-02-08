package pm.listener;

import java.util.Queue;

import pm.Action;

public class ActionListener {
    protected static Queue<Action> actionQueue;

    public static void initialise(Queue<Action> actionQueue) {
        ActionListener.actionQueue = actionQueue;        
    }

    public void add(Action action) {
        actionQueue.add(action);
    }
}
