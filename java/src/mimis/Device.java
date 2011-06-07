package mimis;

import mimis.device.EventMapCycle;
import mimis.event.EventHandler;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.manager.Exitable;
import mimis.manager.Titled;
import mimis.sequence.EventMap;
import mimis.sequence.SequenceListener;
import mimis.sequence.State;

public abstract class Device extends EventHandler implements Titled, Exitable {
    protected String title;
    protected EventMapCycle eventMapCycle;
    protected SequenceListener sequenceListener;

    public Device(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }

    /* Worker */
    public void activate() throws ActivateException {
        super.activate();
        sequenceListener = new SequenceListener(this);
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        sequenceListener.reset();
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

    /* SequenceListener */
    protected void add(EventMap eventMap) {
        sequenceListener.add(eventMap);
    }

    protected void remove(EventMap eventMap) {
        sequenceListener.remove(eventMap);
    }

    protected void add(State state) {
        sequenceListener.add(state);
    }
    
    protected void reset() {
        sequenceListener.reset();
    }
}
