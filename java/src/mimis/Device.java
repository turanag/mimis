package mimis;

import mimis.device.EventMapCycle;
import mimis.event.EventHandler;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.manager.Exitable;
import mimis.manager.Titled;
import mimis.sequence.EventMap;
import mimis.sequence.EventParser;
import mimis.sequence.State;

public abstract class Device extends EventHandler implements Titled, Exitable {
    protected String title;
    protected EventMapCycle eventMapCycle;
    protected EventParser eventParser;

    public Device(String title) {
        this.title = title;
    }

    public String title() {
        return title;
    }

    /* Worker */
    public void activate() throws ActivateException {
        super.activate();
        eventParser = new EventParser(this);
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        eventParser.reset();
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

    /* EventParser */
    protected void add(EventMap eventMap) {
        eventParser.add(eventMap);
    }

    protected void remove(EventMap eventMap) {
        eventParser.remove(eventMap);
    }

    protected void reset() {
        eventParser.reset();
    }
    
    protected void add(State state) {
        eventParser.add(state);
    }
}
