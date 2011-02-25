package pm.task;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.Listener;
import pm.event.Task;
import pm.value.Action;

public abstract class TaskListener extends Listener implements Runnable {
    protected Queue<Task> taskQueue;

    public TaskListener() {
        taskQueue = new ConcurrentLinkedQueue<Task>();
    }

    public final void run() {
        while (run) {
            if (taskQueue.isEmpty()) {
                sleep();
            } else {
                task(taskQueue.poll());
            }
        }
    }

    public void add(Task task) {
        taskQueue.add(task);
    }

    protected void task(Task task) {
        System.out.println(this);
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