package mimis.sequence;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mimis.Event;
import mimis.event.EventHandler;
import mimis.event.EventListener;
import mimis.event.Task;
import mimis.value.Signal;
import mimis.value.Target;

public class SequenceListener {
    protected Log log = LogFactory.getLog(getClass());

    protected EventHandler self;
    protected ArrayList<Sequence> sequenceList;
    protected HashMap<Sequence, Event> eventMap;
    protected ArrayList<Active> activeList;
    
    protected static EventListener eventListener;

    public SequenceListener(EventHandler self) {
        this.self = self;
        sequenceList = new ArrayList<Sequence>();
        eventMap = new HashMap<Sequence, Event>();
        activeList = new ArrayList<Active>();
    }

    public static void initialise(EventListener eventListener) {
        SequenceListener.eventListener = eventListener;
    }
    
    public int add(Sequence sequence, Task task) {
        int id = sequenceList.size();
        sequenceList.add(sequence);
        eventMap.put(sequence, task);
        return id;
    }

    public void add(State state) {
        log.trace(state);
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
    
    protected void add(Event event, Signal signal) {
        if (event instanceof Task) {
            Task task = (Task) event;
            task.setSignal(signal);
        }
        if (event.getTarget().equals(Target.SELF)) {
            eventListener.add(event);
        } else {
            self.add(event);
        }
    }
}
