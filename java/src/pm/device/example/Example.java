package pm.device.example;

import java.util.Queue;

import pm.action.Action;
import pm.device.Device;
import pm.event.Event;
import pm.event.Type;

public class Example extends Device {
    public Example(Queue<Event> eventQueue) {
        super(eventQueue);
    }

    public void initialise() {
        eventQueue.add(new Event(Type.APPLICATION, Action.START));
        eventQueue.add(new Event(Type.APPLICATION, Action.TEST));
        eventQueue.add(new Event(Type.MAIN, Action.EXIT));
    }
}
