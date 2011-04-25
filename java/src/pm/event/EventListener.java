package pm.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.Event;
import pm.Listener;
import pm.event.task.Continuous;
import pm.value.Action;

public abstract class EventListener extends Listener implements Runnable {
    protected Queue<Event> eventQueue;

    public EventListener() {
        eventQueue = new ConcurrentLinkedQueue<Event>();
    }

    public final void run() {
        while (run) {
            if (eventQueue.isEmpty()) {
                sleep();
            } else {
                event(eventQueue.poll());
            }
        }
    }

    public void add(Event event) {
        eventQueue.add(event);
    }

    protected void event(Event event) {
        if (event instanceof Feedback) {
            event((Feedback) event);
        } else if (event instanceof Task) {
            event((Task) event);
        }
    }

    protected void event(Feedback feedback) {}

    protected void event(Task task) {
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