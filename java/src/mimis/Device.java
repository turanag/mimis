package mimis;

import mimis.event.EventHandler;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.manager.Exitable;
import mimis.manager.Titled;
import mimis.sequence.EventMap;
import mimis.sequence.SequenceParser;
import mimis.sequence.State;

public abstract class Device extends EventHandler implements Titled, Exitable {
    protected String title;
    protected SequenceParser sequenceParser;

    public Device(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }

    /* Worker */
    public void activate() throws ActivateException {
        super.activate();
        sequenceParser = new SequenceParser(this);
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        sequenceParser.reset();
    }

    public void stop() throws DeactivateException {
        if (active()) {
            try {
                deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        }
        super.stop();
    }

    /* SequenceParser */
    protected void add(EventMap eventMap) {
        sequenceParser.add(eventMap);
    }

    protected void remove(EventMap eventMap) {
        sequenceParser.remove(eventMap);
    }

    protected void reset() {
        sequenceParser.reset();
    }
    
    protected void add(State state) {
        sequenceParser.add(state);
    }
}
