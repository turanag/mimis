package pm.device;

import java.util.ArrayList;
import java.util.Queue;

import pm.event.Event;


public abstract class Device {
    protected Queue<Event> eventQueue;

    protected Device(Queue<Event> eventQueue) {
        this.eventQueue = eventQueue;
        initialise();
    }

    public void initialise() {}
}
