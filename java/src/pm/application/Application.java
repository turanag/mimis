package pm.application;

import java.util.LinkedList;
import java.util.Queue;

import pm.Action;

public abstract class Application { //WinampController.
    Queue<Action> actionQueue;
    
    public Application() {
        actionQueue = new LinkedList<Action>();
    }

    public void start() {}
    public void exit() {}
}