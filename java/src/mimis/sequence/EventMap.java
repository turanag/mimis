package mimis.sequence;

import java.util.HashMap;

import mimis.Button;
import mimis.Event;
import mimis.Macro;
import mimis.event.Task;
import mimis.sequence.state.Press;

public class EventMap extends HashMap<Sequence, Event> {
    protected static final long serialVersionUID = 1L;

    public void add(Button button, Task task) {
        add(new Press(button), task);
    }

    public void add(Press press, Task task) {
        add(press, task, true);
    }

    protected void add(Press press, Task task, boolean macro) {
        if (macro) {
            add(new Macro(press), task);
        } else {
            add((State) press, task);
        }
    }

    protected void add(State state, Task task) {
        add(new Sequence(state), task);
    }

    public void add(Sequence sequence, Task task) {
        put(sequence, task);
    }
}
