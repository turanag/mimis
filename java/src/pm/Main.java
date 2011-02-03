package pm;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.action.Action;
import pm.applicatie.voorbeeld.Voorbeeld;
import pm.application.Application;
import pm.device.Device;
import pm.device.example.Example;
import pm.event.Event;
import pm.exception.ActionException;
import pm.exception.ActionNotImplementedException;
import pm.exception.EventException;

public class Main extends Application {
    protected static final int SLEEP = 100;

    ArrayList<Application> applicationList;
    ArrayList<Device> deviceList;
    Queue<Event> eventQueue;

    boolean run;
    Application currentApplication;

    public Main() {
        applicationList = new ArrayList<Application>();
        deviceList = new ArrayList<Device>();
        eventQueue = new ConcurrentLinkedQueue<Event>();
    }

    public void addApplication(Application application) {
        applicationList.add(application);
    }

    public boolean removeApplication(Application application) {
        return applicationList.remove(application);
    }

    public void addDevice(Device device) {
        deviceList.add(device);
    }

    public boolean removeDevie(Device device) {
        return deviceList.remove(device);
    }

    public void start() throws Exception {
        addDevice(new Example(eventQueue));
        Application application = new Voorbeeld();
        addApplication(application);
        currentApplication = application;
        run();
    }

    public void run() throws ActionException, EventException {
        run = true;
        while (run) {
            if (eventQueue.isEmpty()) {
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {}
            } else {
                Event event = eventQueue.poll();
                Action action = event.getAction();
                switch (event.getType()) {
                    case MAIN:
                        invoke(action);
                        break;
                    case APPLICATION:
                        try {
                            currentApplication.invoke(action);
                        } catch (ActionNotImplementedException e) {
                            // Todo: log.write(...)
                        }
                        break;
                    default:
                        throw new EventException("Unknown event type");
                }
            }
        }
    }

    public void exit() {
        run = false;
    }

    public static void main(String[] args) {
        try {
            new Main().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}