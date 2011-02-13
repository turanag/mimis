package pm.task;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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

    abstract protected void task(Task task);
}
