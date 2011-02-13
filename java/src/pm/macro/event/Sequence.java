package pm.macro.event;

import pm.macro.Event;


public class Sequence {
    protected Event[] eventArray;

    public Sequence(Event... eventArray) {
        this.eventArray = eventArray;
    }

    public int count() {
        return eventArray.length;
    }

    public Event get(int i) {
        return eventArray.length > 0 ? eventArray[i] : null;
    }
}
