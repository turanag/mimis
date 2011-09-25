package mimis.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import mimis.Event;
import mimis.Worker;

public abstract class EventListener extends Worker {
    protected Queue<Event> eventQueue;

    public EventListener() {
        eventQueue = new ConcurrentLinkedQueue<Event>();
    }

    public void add(Event event) {
        log.debug("[EventListener] Add event:" + event + " " + event.getTarget());
        eventQueue.add(event);
        synchronized (this) {
            notifyAll();
        }
    }

    public final void work() {
        while (!eventQueue.isEmpty()) {
            event(eventQueue.poll());
        }
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.info(e);
            }
        }
    }

    public abstract void event(Event event);
}