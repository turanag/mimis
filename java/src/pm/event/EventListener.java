package pm.event;

import pm.Event;
import pm.Listener;
import pm.event.task.Continuous;
import pm.value.Action;

public abstract class EventListener extends Listener implements Runnable {
    protected static EventManager eventManager;

    public static void initialise(EventManager eventManager) {
        EventListener.eventManager = eventManager;
    }
    
    public final void run() {
        while (run) {
             event(eventManager.get());
        }
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