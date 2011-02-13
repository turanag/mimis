package pm;

import java.util.ArrayList;

import pm.exception.MacroException;
import pm.exception.macro.MacroEventOrderException;
import pm.macro.Event;
import pm.macro.event.Sequence;
import pm.macro.event.Hold;
import pm.macro.event.Press;
import pm.macro.event.Release;

public class Macro extends Sequence {
    public Macro(Press press) {
        Button button = press.getButton();
        this.eventArray = new Event[] {press, new Release(button)};
    }

    public Macro(Event... eventArray) throws MacroException {
        ArrayList<Button> holdList = new ArrayList<Button>();
        ArrayList<Event> eventList = new ArrayList<Event>();
        for (Event event : eventArray) {
            Button button = event.getButton();
            if (event instanceof Press) {
                if (holdList.contains(button)) {
                    throw new MacroEventOrderException("Press events cannot follow hold events for the same button.");
                }
                eventList.add(event);
                eventList.add(new Release(button));
            } else if (event instanceof Release) {
                if (!holdList.contains(button)) {
                    throw new MacroEventOrderException("Cannot release a button that is not held.");
                }
                holdList.remove(button);
                eventList.add(event);
            } else if (event instanceof Hold) {
                if (holdList.contains(button)) {
                    throw new MacroEventOrderException("Cannot hold a button more than once.");
                }
                holdList.add(button);
                eventList.add(new Press(button));
            }
        }
        if (!holdList.isEmpty()) {
            throw new MacroEventOrderException("One or more buttons are not released.");
        }
        eventArray = (Event[]) eventList.toArray(new Event[0]);
    }
}