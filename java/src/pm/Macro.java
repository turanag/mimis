package pm;

import pm.macro.Event;

public class Macro {
    protected Event[] eventArray;

    public Macro(Event... eventArray) {
        this.eventArray = eventArray;
    }

    public int count() {
        return eventArray.length;
    }
}