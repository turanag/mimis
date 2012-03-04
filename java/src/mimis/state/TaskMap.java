package mimis.state;

import java.util.HashMap;

import mimis.input.Button;
import mimis.input.Input;
import mimis.input.Task;
import mimis.input.state.Press;
import mimis.input.state.State;
import mimis.input.state.sequence.Macro;
import mimis.input.state.sequence.Sequence;

public class TaskMap extends HashMap<Sequence, Task> implements Input {
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

    public void add(State state, Task task) {
        add(new Sequence(state), task);
    }

    public void add(Sequence sequence, Task task) {
        put(sequence, task);
    }

}
