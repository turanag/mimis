package pm;

import java.util.ArrayList;

import pm.exception.MacroException;
import pm.exception.macro.MacroEventOrderException;
import pm.macro.Sequence;
import pm.macro.State;
import pm.macro.state.Hold;
import pm.macro.state.Press;
import pm.macro.state.Release;

public class Macro extends Sequence {
    public Macro(Press press) {
        Button button = press.getButton();
        this.eventArray = new State[] {press, new Release(button)};
    }

    public Macro(State... eventArray) throws MacroException {
        ArrayList<Button> holdList = new ArrayList<Button>();
        ArrayList<State> eventList = new ArrayList<State>();
        for (State state : eventArray) {
            Button button = state.getButton();
            if (state instanceof Press) {
                if (holdList.contains(button)) {
                    throw new MacroEventOrderException("Press events cannot follow hold events for the same button.");
                }
                eventList.add(state);
                eventList.add(new Release(button));
            } else if (state instanceof Release) {
                if (!holdList.contains(button)) {
                    throw new MacroEventOrderException("Cannot release a button that is not held.");
                }
                holdList.remove(button);
                eventList.add(state);
            } else if (state instanceof Hold) {
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
        eventArray = (State[]) eventList.toArray(new State[0]);
    }
}