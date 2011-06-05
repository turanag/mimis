package mimis;

import mimis.event.EventHandler;
import mimis.event.Task;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.manager.Exitable;
import mimis.manager.Titled;
import mimis.sequence.Sequence;
import mimis.sequence.SequenceListener;
import mimis.sequence.State;
import mimis.sequence.state.Press;

public abstract class Device extends EventHandler implements Titled, Exitable {
    protected String title;
    protected SequenceListener sequenceListener;

    public Device(String title) {
        this.title = title;
    }

    public void activate() throws ActivateException {
        super.activate();
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

    /* Recognize events */
    protected void add(State state) {
        log.debug(state.toString() + " " + state.getButton());
        sequenceListener.add(state);
    }

    public String title() {
        return title;
    }

    public void stop() {
        if (active()) {
            try {
                deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        }
        super.stop();
    }
}
