package pm.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import pm.Event;
import pm.Worker;

public abstract class EventListener extends Worker {
    protected Queue<Event> eventQueue;
    protected Object available;

    public EventListener() {
        eventQueue = new ConcurrentLinkedQueue<Event>();
        available = new Object();
    }

    public void add(Event event) {
        eventQueue.add(event);
        synchronized (available) {
            available.notifyAll();
        }
    }

    /*public Event get(Target target) {
        while (eventQueue.isEmpty()) {
            synchronized (available) {
                try {
                    available.await();
                } catch (InterruptedException e) {}
            }
        }
        Event event = eventQueue.peek();
        if (event instanceof Task) {
            Task task = (Task) event;
            if (task.getTarget() == target) {
                return eventQueue.poll();
            } else {
                return null;
            }
        } else {
            return eventQueue.poll();
        }
    }

    public Event gett(Target target) {
        while (true) {
            Event event = eventQueue.peek();
            if (event instanceof Task) {
                Task task = (Task) event;
                if (task.getTarget() == target) {
                    return get();
                }
            }
        }
    }

    public Event get() {
        return get(Target.ALL);
    }*/

    public final void run() {
        while (run) {
            while (eventQueue.isEmpty()) {
                synchronized (available) {
                    try {
                        available.wait();
                    } catch (InterruptedException e) {}
                }
            }
            event(eventQueue.poll());
       }
    }

    public abstract void event(Event event);

    /*public static void add(Feedback feedback) {
        for (EventListener eventListener : eventListenerList) {
            eventListener.add(feedback);
        }
    }*/

    /*public static void add(EventListener self, Task task) {
        if (task instanceof Stopper) {
            ((Stopper) task).stop();
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
                default: {
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
    }*/
}