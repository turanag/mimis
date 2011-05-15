package pm.event;

import pm.Event;
import pm.event.task.Continuous;
import pm.value.Action;

public abstract class EventHandler extends EventListener {
    protected static EventRouter eventRouter;

    public static void initialise(EventRouter eventRouter) {
        EventHandler.eventRouter = eventRouter;
    }

    protected void initialise() {
        eventRouter.add(this);
    }

    public void event(Event event) {
        System.out.println(event);
        if (event instanceof Feedback) {
            feedback((Feedback) event);
        } else if (event instanceof Task) {
            task((Task) event);
        }
    }

    protected void feedback(Feedback feedback) {}

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