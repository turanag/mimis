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
}