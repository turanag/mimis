package pm.device.example;

import java.util.Queue;

import pm.action.Action;
import pm.device.Device;
import pm.event.Event;

public class Example extends Device {
    public Example(Queue<Event> eventQueue) {
        super(eventQueue);
    }

    public void initialise() {
        Event event = new Event(Action.START);
        eventQueue.add(event);
    }
}
