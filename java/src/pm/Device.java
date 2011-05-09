package pm;

import pm.event.Task;
import pm.event.EventHandler;
import pm.event.task.Continuous;
import pm.event.task.Stopper;
import pm.exception.InitialiseException;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.Sequence;
import pm.macro.SequenceListener;
import pm.macro.State;
import pm.macro.state.Hold;
import pm.macro.state.Press;
import pm.macro.state.Release;

public abstract class Device extends EventHandler {
    protected SequenceListener sequenceListener;

    static {
        SequenceListener.initialise(eventSpreader);
    }

    public Device() {
        super();
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

    /* Device default methods */
    public void initialise() throws InitialiseException {
        super.initialise();
    }

    public void exit() throws DeviceExitException {
        stop();
    }
}
