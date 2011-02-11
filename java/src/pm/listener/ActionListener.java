package pm.listener;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.Action;

public abstract class ActionListener implements Runnable {
    protected static final int SLEEP = 100;

    protected Queue<Action> actionQueue;
    protected boolean run;

    public ActionListener() {
        actionQueue = new ConcurrentLinkedQueue<Action>();
    }

    public void start() throws Exception {
        new Thread(this).start();
    }

    public void run() {
        run = true;
        while (run) {
            if (actionQueue.isEmpty()) {
                sleep(SLEEP);
            } else {
                Action action = actionQueue.poll();
                action(action);
            }
        }
    }

    public void add(Action action) {
        actionQueue.add(action);
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {}
    }

    abstract protected void action(Action action);
}
