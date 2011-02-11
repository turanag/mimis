package pm;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.application.Application;
import pm.application.Winamp.WinampApplication;
import pm.application.iTunes.iTunesApplication;
import pm.application.voorbeeld.VoorbeeldApplication;
import pm.device.Device;
import pm.device.javainput.extreme3d.Extreme3DDevice;
import pm.device.javainput.rumblepad.RumblepadDevice;
import pm.device.jintellitype.JIntellitypeDevice;
import pm.device.wiimote.WiimoteDevice;
import pm.exception.ActionException;
import pm.exception.action.NotImplementedActionException;
import pm.exception.action.UnknownTargetException;
import pm.listener.ActionListener;
import pm.util.ArrayCycle;

public class Main {
    protected static final int SLEEP = 100;

    ArrayCycle<Application> applicationCycle;
    ArrayList<Device> deviceList;
    Queue<Action> actionQueue;

    boolean run;

    public Main() {
        applicationCycle = new ArrayCycle<Application>();
        deviceList = new ArrayList<Device>();
        actionQueue = new ConcurrentLinkedQueue<Action>();
        ActionListener.initialise(actionQueue);
    }

    public void add(Application application) {
        applicationCycle.add(application);
    }

    public boolean remove(Application application) {
        return applicationCycle.remove(application);
    }

    public void add(Device device) {
        deviceList.add(device);
    }

    public boolean remove(Device device) {
        return deviceList.remove(device);
    }

    public void start() throws Exception {
        //add(new ExampleDevice());
        add(new RumblepadDevice());
        //add(new Extreme3DDevice());
        //add(new JIntellitypeDevice());
        //add(new WiimoteDevice());
        for (Device device : deviceList) {
            device.start();
        }
    
        add(new VoorbeeldApplication());
        add(new iTunesApplication());
        add(new WinampApplication());
        applicationCycle.next();
        for (Application application : applicationCycle) {
            application.start();
        }
        run();
    }

    public void run() throws ActionException {
        run = true;
        while (run) {
            if (actionQueue.isEmpty()) {
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {}
            } else {
                Action action = actionQueue.poll();
                Object object;
                System.out.println("Action: " + action + " Target: " + action.getTarget());
                switch (action.getTarget()) {
                    case MAIN:
                        object = this;
                        break;
                    case APPLICATION:
                        object = applicationCycle.current();
                        System.out.println("Current application: " + object.getClass());
                        break;
                    default:
                        throw new UnknownTargetException();
                }
                try {
                    action.invoke(object);
                } catch (NotImplementedActionException e) {
                    e.printStackTrace();
                    // Todo: log.write(...)
                }
            }
        }
    }

    public void exit() {
        run = false;
        for (Device device : deviceList) {
            device.exit();
        }
        for (Application application : applicationCycle) {
            application.exit();
        }
        System.out.println("Main exit...");
    }

    public static void main(String[] args) {
        try {
            new Main().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}