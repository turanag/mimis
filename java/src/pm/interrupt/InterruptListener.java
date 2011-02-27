package pm.interrupt;

import java.util.ArrayList;

import pm.event.EventListener;
import pm.event.EventManager;
import pm.macro.State;

public class InterruptListener {
    protected EventListener eventListener;
    protected ArrayList<Interruptible> interruptibleList;
    
    public InterruptListener(EventListener eventListener) {
        this.eventListener = eventListener;
        interruptibleList = new ArrayList<Interruptible>();
    }

    public void add(Interruptible interruptible) {
        interruptibleList.add(interruptible);
    }
    
    public void add(State state) {
        for (Interruptible interruptible : interruptibleList) {
            if(interruptible.triggerPress.equals(state)) {
                EventManager.add(interruptible.triggerTask);
                interruptible.triggered = true;
            } else if(interruptible.interruptPress.equals(state) && interruptible.triggered) {
                EventManager.add(interruptible.interruptTask);
                interruptible.interrupted = true;
                interruptibleList.remove(interruptible);
            }
        }
    }
}
