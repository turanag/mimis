package pm.event;

import java.util.ArrayList;

import pm.Application;
import pm.Device;
import pm.Main;
import pm.application.ApplicationCycle;
import pm.event.task.Stopper;
import pm.value.Target;

public class EventManager {
    protected static ArrayList<EventListener> eventListenerList;
    protected static ApplicationCycle applicationCycle;

    public static void initialise(ApplicationCycle applicationCycle) {
        eventListenerList = new ArrayList<EventListener>();
        EventManager.applicationCycle = applicationCycle;
    }

    public static void add(EventListener eventListener) {
        eventListenerList.add(eventListener);
    }

    public static void add(Feedback feedback) {
        for (EventListener eventListener : eventListenerList) {
            eventListener.add(feedback);
        }
    }

    public static void add(EventListener self, Task task) {
        if (task instanceof Stopper) {
            Stopper stopper = (Stopper) task;
            stopper.stop();           
        } else {
            Target target = task.getTarget();
            switch (target) {
                case SELF:
                    self.add(task);
                    break;
                case APPLICATION:
                    if (applicationCycle.size() > 0) {
                        applicationCycle.current().add(task);
                    }
                    break;
                default:
                    for (EventListener eventListener : eventListenerList) {
                        switch (target) {
                            case ALL:
                                eventListener.add(task);
                                break;
                            case MAIN:
                                if (eventListener instanceof Main) {
                                    eventListener.add(task);
                                } 
                                break;
                            case DEVICES:
                                if (eventListener instanceof Device) {
                                    eventListener.add(task);
                                } 
                                break;
                            case APPLICATIONS:
                                if (eventListener instanceof Application) {
                                    eventListener.add(task);
                                } 
                                break;
                        }
                    }
            }
        }
    }

    public static void add(Task task) {
        if (!task.getTarget().equals(Target.SELF)) {
            add(null, task);
        }
    }

    public static void remove(EventListener eventListener) {
        eventListenerList.remove(eventListener);
    }
}