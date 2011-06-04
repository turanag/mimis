package mimis.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import mimis.Event;
import mimis.Worker;

public abstract class EventListener extends Worker {
    protected Queue<Event> eventQueue;
    protected Object work;

    public EventListener() {
        eventQueue = new ConcurrentLinkedQueue<Event>();
        work = new Object();
    }

    public void add(Event event) {
        log.info("event " + event + " " + active);
        eventQueue.add(event);
        synchronized (work) {
            work.notifyAll();
        }
    }

    public final void work() {
        while (eventQueue.isEmpty()) {
            synchronized (work) {
                try {
                    work.wait();
                } catch (InterruptedException e) {}
                if (!running) {
                    return;
                }
            }
        }
        event(eventQueue.poll());
   }

    public void stop() {
        super.stop();
        synchronized (work) {
            work.notifyAll();
        }
    }

    public abstract void event(Event event);
}