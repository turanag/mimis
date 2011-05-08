package pm.event;

import java.util.ArrayList;

import pm.Application;

public abstract class EventSpreader extends EventListener {
    protected ArrayList<EventListener> eventListenerList;
    protected Application application;

    public void set(Application application) {
        this.application = application;
    }

    public EventSpreader() {
        eventListenerList = new ArrayList<EventListener>();
    }

    public void add(EventListener eventListener) {
        eventListenerList.add(eventListener);
    }

    public void remove(EventListener eventListener) {
        eventListenerList.remove(eventListener);
    }
}
