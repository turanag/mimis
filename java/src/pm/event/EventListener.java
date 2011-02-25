package pm.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.Listener;
import pm.event.task.Continuous;
import pm.value.Action;

public abstract class EventListener extends Listener implements Runnable {
    protected Queue<Task> taskQueue;

    public EventListener() {
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