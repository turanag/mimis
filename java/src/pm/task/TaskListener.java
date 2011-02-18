package pm.task;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.Action;
import pm.Task;

public abstract class TaskListener implements Runnable {
    protected static final int SLEEP = 100;

    protected Queue<Task> taskQueue;
    protected boolean run;

    public TaskListener() {
        taskQueue = new ConcurrentLinkedQueue<Task>();
        run = false;
    }

    public void start() {
        new Thread(this).start();
    }

    public void run() {
        run = true;
        while (run) {
            if (taskQueue.isEmpty()) {
                sleep(SLEEP);
            } else {
                task(taskQueue.poll());
            }
        }
    }

    public void stop() {
        run = false;
    }

    public void add(Task task) {
        taskQueue.add(task);
    }

    protected void sleep(int time) {
        try {
            if (time > 0) {
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {}
    }

    protected void task(Task task) {
        Action action = task.getAction();
        if (task instanceof Continuous) {
            Continuous continuous = (Continuous) task;
            do {
                action(action);
                continuous.nextIteration();
                sleep(continuous.getSleep());
            } while (run && !continuous.getStop());
            continuous.reset();
        } else {
            action(action);
        }        
    }

    protected void action(Action action) {}
}