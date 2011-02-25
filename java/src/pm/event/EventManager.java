package pm.event;

import java.util.ArrayList;

import pm.Application;
import pm.Device;
import pm.Main;
import pm.application.ApplicationCycle;
import pm.event.task.Stopper;
import pm.value.Target;

public class EventManager {
    protected static ArrayList<EventListener> taskListenerList;
    protected static ApplicationCycle applicationCycle;

    public static void initialise(ApplicationCycle applicationCycle) {
        taskListenerList = new ArrayList<EventListener>();
        EventManager.applicationCycle = applicationCycle;
    }

    public static void add(EventListener eventListener) {
        taskListenerList.add(eventListener);
    }

    public static void add(Feedback feedback) {
        for (EventListener eventListener : taskListenerList) {
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
                    applicationCycle.current().add(task);
                    break;
                default:
                    for (EventListener eventListener : taskListenerList) {
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
                        break;
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
        taskListenerList.remove(eventListener);
    }
}