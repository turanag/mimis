package pm;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.action.Action;
import pm.application.Application;
import pm.application.voorbeeld.VoorbeeldApplication;
import pm.device.Device;
import pm.device.JavaInputDevice;
import pm.device.example.ExampleDevice;
import pm.device.rumblepad.RumblepadDevice;
import pm.exception.ActionException;
import pm.exception.ActionNotImplementedException;
import pm.exception.EventException;
import pm.service.javainput.JavaInputService;

public class Main extends Target {
    protected static final int SLEEP = 100;

    ArrayList<Application> applicationList;
    ArrayList<Device> deviceList;
    Queue<Action> actionQueue;

    boolean run;
    Application currentApplication;
    
    public Main() {
        applicationList = new ArrayList<Application>();
        //applicationList.iterator();
        deviceList = new ArrayList<Device>();
        actionQueue = new ConcurrentLinkedQueue<Action>();
        JavaInputService.initialize();
        //JXInputDevice.jxinputService = new JXInputService();
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
        Device device = new ExampleDevice(actionQueue);
        //addDevice(device);
        
        device = new RumblepadDevice(actionQueue);
        
        addDevice(device);
        device.initialise();
        Application application = new VoorbeeldApplication();
        addApplication(application);
        currentApplication = application;
        run();
    }

    public void run() throws ActionException, EventException {
        run = true;
        while (run) {
            if (actionQueue.isEmpty()) {
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {}
            } else {
                Action action = actionQueue.poll();
                Target target;
                switch (action.getTarget()) {
                    case MAIN:
                        target = this;
                        invoke(action);
                        break;
                    case APPLICATION:
                        target = currentApplication;
                        break;
                    default:
                        throw new EventException("Unknown event type");
                }
                try {
                    target.invoke(action);
                } catch (ActionNotImplementedException e) {
                    // Todo: log.write(...)
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