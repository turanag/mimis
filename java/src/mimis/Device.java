package mimis;

import mimis.event.EventHandler;
import mimis.event.Task;
import mimis.event.task.Continuous;
import mimis.event.task.Stopper;
import mimis.macro.Sequence;
import mimis.macro.SequenceListener;
import mimis.macro.State;
import mimis.macro.state.Hold;
import mimis.macro.state.Press;
import mimis.macro.state.Release;
import mimis.manager.Titled;

public abstract class Device extends EventHandler implements Titled, Exitable {
    protected String title;
    protected boolean active;
    protected SequenceListener sequenceListener;

    static {
        SequenceListener.initialise(eventRouter);
    }

    public Device(String title) {
        this.title = title;
        active = false;
        sequenceListener = new SequenceListener(this);
    }

    /* Register macro's */
    protected void add(Sequence sequence, Task task) {
        sequenceListener.add(sequence, task);
    }

    protected void add(State state, Task task) {
        add(new Sequence(state), task);
    }

    protected void add(Press press, Task task, boolean macro) {
        if (macro) {
            add(new Macro(press), task);
        } else {
            add((State) press, task);
        }
    }

    protected void add(Press press, Task task) {
        add(press, task, true);
    }

    protected void add(Hold hold, Task pressTask, Task releaseTask) {
        Button button = hold.getButton();
        add(new Press(button), pressTask, false);
        add(new Release(button), releaseTask);
    }

    protected void add(Sequence startSequence, Sequence stopSequence, Continuous continuous) {
        add(startSequence, continuous);
        add(stopSequence, new Stopper(continuous));
    }

    protected void add(State startEvent, State stopEvent, Continuous continuous) {
        add(startEvent, continuous);
        add(stopEvent, new Stopper(continuous));
    }

    protected void add(Press startPress, Press stopPress, Continuous continuous) {
        add(new Macro(startPress), continuous);
        add(new Macro(stopPress), new Stopper(continuous));
    }

    protected void add(Hold hold, Continuous continuous) {
        Button button = hold.getButton();
        add(new Press(button), new Release(button), continuous);
    }

    /* Recognize events */
    protected void add(State state) {
        sequenceListener.add(state);
    }

    public String title() {
        return title;
    }

    public void exit() {
        if (active()) {
            deactivate();
        }
        stop();
    }
}
