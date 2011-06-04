package mimis.event;

import java.util.ArrayList;

import mimis.Application;


public abstract class EventRouter extends EventListener {
    protected ArrayList<EventListener> eventListenerList;
    protected Application application;

    public void set(Application application) {
        this.application = application;
    }

    public EventRouter() {
        eventListenerList = new ArrayList<EventListener>();
    }

    public void add(EventListener... eventListenerArray) {
        for (EventListener eventListener : eventListenerArray) {
            eventListenerList.add(eventListener);
        }
    }

    public void remove(EventListener eventListener) {
        eventListenerList.remove(eventListener);
    }
}
