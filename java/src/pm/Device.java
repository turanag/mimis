package pm;

import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.Event;
import pm.macro.event.Hold;
import pm.macro.event.Press;
import pm.macro.event.Release;
import pm.macro.event.Sequence;
import pm.macro.event.SequenceListener;
import pm.task.Continuous;
import pm.task.Stopper;

public abstract class Device {
    protected SequenceListener sequenceListener;

    public Device() {
        sequenceListener = new SequenceListener();
    }

    /* Register macro's */
    protected void add(Sequence sequence, Task task) {
        sequenceListener.add(sequence, task);
    }

    protected void add(Event event, Task task) {
        add(new Sequence(event), task);
    }

    protected void add(Press press, Task task, boolean macro) {
        if (macro) {
            add(new Macro(press), task);
        } else {
            add((Event) press, task);
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

    protected void add(Event startEvent, Event stopEvent, Continuous continuous) {
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
    protected void add(Event event) {
        sequenceListener.add(event);
    }

    /* Device default methods */
    public void initialise() throws DeviceInitialiseException {}
    public void exit() throws DeviceExitException {}
    public void action(Action action) {}
}
