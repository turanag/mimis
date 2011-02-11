package pm.action;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.Action;

public abstract class ActionListener implements Runnable {
    protected static final int SLEEP = 100;

    protected Queue<Action> actionQueue;
    protected boolean run;

    public ActionListener() {
        actionQueue = new ConcurrentLinkedQueue<Action>();
        run = true;
    }

    public void start() {
        new Thread(this).start();
    }

    public void run() {
        while (run) {
            if (actionQueue.isEmpty()) {
                sleep(SLEEP);
            } else {
                action(actionQueue.poll());
            }
        }
    }

    public void stop() {
        run = false;
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
