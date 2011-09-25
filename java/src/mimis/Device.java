package mimis;

import mimis.event.EventHandler;
import mimis.exception.worker.DeactivateException;
import mimis.manager.Titled;
import mimis.sequence.EventMap;
import mimis.sequence.SequenceParser;
import mimis.sequence.State;

public abstract class Device extends EventHandler implements Titled {
    protected String title;
    protected SequenceParser sequenceParser;

    public Device(String title) {
        this.title = title;
        sequenceParser = new SequenceParser(this);
    }

    public String title() {
        return title;
    }

    /* Worker */
    protected void deactivate() throws DeactivateException {
        super.deactivate();
        sequenceParser.reset();
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
