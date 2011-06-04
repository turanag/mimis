package mimis.macro;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mimis.Event;
import mimis.event.EventHandler;
import mimis.event.EventListener;
import mimis.event.Task;
import mimis.value.Target;

public class SequenceListener {
    protected Log log = LogFactory.getLog(getClass());

    protected EventHandler eventHandler;
    protected ArrayList<Sequence> sequenceList;
    protected HashMap<Sequence, Event> eventMap;
    protected ArrayList<Active> activeList;
    
    protected static EventListener eventListener;

    public SequenceListener(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
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
                if (active.last()) {
                    Event event = eventMap.get(active.getSequence());
                    if (event.getTarget().equals(Target.SELF)) {
                        eventHandler.event(event);
                    } else {
                        eventListener.add(event);
                    }
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
}
