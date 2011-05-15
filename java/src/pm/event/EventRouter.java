package pm.event;

import java.util.ArrayList;

import pm.Application;

public abstract class EventRouter extends EventListener {
    protected ArrayList<EventListener> eventListenerList;
    protected Application application;

    public void set(Application application) {
        this.application = application;
    }

    public EventRouter() {
        eventListenerList = new ArrayList<EventListener>();
    }

    public void add(EventListener eventListener) {
        eventListenerList.add(eventListener);
    }

    public void remove(EventListener eventListener) {
        eventListenerList.remove(eventListener);
    }
}
