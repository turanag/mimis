package mimis.sequence;

import java.util.ArrayList;
import java.util.Map.Entry;

import mimis.Event;
import mimis.event.EventHandler;
import mimis.event.EventListener;
import mimis.event.Task;
import mimis.value.Signal;
import mimis.value.Target;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SequenceParser {
    protected Log log = LogFactory.getLog(getClass());

    protected EventHandler self;
    protected EventMap eventMap;
    protected ArrayList<Sequence> sequenceList;
    protected ArrayList<Active> activeList;

    protected static EventListener eventListener;

    public SequenceParser(EventHandler self) {
        this.self = self;
        eventMap = new EventMap();
        sequenceList = new ArrayList<Sequence>();
        activeList = new ArrayList<Active>();
    }

    public static void initialise(EventListener eventListener) {
        SequenceParser.eventListener = eventListener;
    }

    public synchronized void add(EventMap eventMap) {
        this.eventMap.putAll(eventMap);
        sequenceList.addAll(eventMap.keySet());
    }

    public synchronized void remove(EventMap eventMap) {
        for (Entry<Sequence, Event> entry : eventMap.entrySet()) {
            Sequence sequence = entry.getKey();
            this.eventMap.remove(sequence);
            sequenceList.remove(sequence);
            activeList.remove(sequence);
        }        
    }

    public synchronized void reset() {
        eventMap.clear();
        sequenceList.clear();
        activeList.clear();
    }

    public synchronized void add(State state) {
        for (Sequence sequence : sequenceList) {
            activeList.add(new Active(sequence));
        }
        ArrayList<Active> removeList = new ArrayList<Active>();
        for (Active active : activeList) {
            if (active.next(state)) {
                Event event = eventMap.get(active.getSequence());
                if (active.first()) {
                    add(event, Signal.BEGIN);
                } else if (active.last()) {
                    add(event, Signal.END);
                    removeList.add(active);
                }
            } else {
                removeList.add(active);
            }
        }
        for (Active active : removeList) {
            activeList.remove(active);
        }
    }

    protected synchronized void add(Event event, Signal signal) {
        if (event instanceof Task) {
            event = ((Task) event).setSignal(signal);
        }
        if (event.getTarget().equals(Target.SELF)) {
            self.add(event);
        } else {
            eventListener.add(event);
        }
    }
}
