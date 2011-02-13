package pm;

import java.util.ArrayList;

import pm.application.Winamp.WinampApplication;
import pm.application.example.ExampleApplication;
import pm.application.iTunes.iTunesApplication;
import pm.device.gui.GUIDevice;
import pm.device.javainput.rumblepad.RumblepadDevice;
import pm.device.jintellitype.JIntellitypeDevice;
import pm.device.wiimote.WiimoteDevice;
import pm.exception.application.ApplicationExitException;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.task.TaskGatherer;
import pm.task.TaskListener;
import pm.util.ArrayCycle;

public class Main extends TaskListener {
    //protected String[] deviceClassArray;
    protected ArrayCycle<Application> applicationCycle;
    protected ArrayList<Device> deviceList;

    public Main() {
        super();
        /*deviceClassArray = new String[] {
            "pm.device.jintellitype.JIntellitypeDevice",
            "pm.device.javainput.rumblepad.RumblepadDevice",
            "pm.device.javainput.extreme3d.Extreme3DDevice",
            "pm.device.wiimote.WiimoteDevice"};*/
        applicationCycle = new ArrayCycle<Application>();
        deviceList = new ArrayList<Device>();
        TaskGatherer.initialise(taskQueue);
    }

    public void initialise() throws DeviceInitialiseException {
        add(new JIntellitypeDevice());
        //add(new RumblepadDevice());
        add(new WiimoteDevice());
        //add(new GUIDevice());
        for (Device device : deviceList) {
            device.initialise();
        }

        add(new ExampleApplication());
        //add(new WinampApplication());
        //add(new iTunesApplication());
        applicationCycle.next();
        for (Application application : applicationCycle) {
            application.start();
        }
    }

    public void exit() {
        try {
            System.out.println("Exit devices...");
            for (Device device : deviceList) {
                device.exit();    
            }
            System.out.println("Exit applications...");
            for (Application application : applicationCycle) {
                application.exit();
            }
        } catch (DeviceExitException e) {
        } catch (ApplicationExitException e) {}
        System.out.println("Exit main...");
        stop();
    }

    protected void action(Action action) {
        System.out.println("Main: " + action);
        switch (action) {
            case NEXT:
                applicationCycle.next();
                break;
            case PREVIOUS:
                applicationCycle.previous();
                break;
            case EXIT:
                exit();
                break;
        }
    }

    protected void task(Task task) {
        Action action = task.getAction();
        Target target = task.getTarget();
        System.out.println("Action: " + action + " Target: " + target);
        switch (target) {
            case MAIN:
                action(action);
                break;
            case APPLICATION:
                applicationCycle.current().add(task);
                break;
        }

    }

    /*protected void addDevices() throws DeviceInitialiseException {
        for (String deviceClass : deviceClassArray) {
            try {
                Object object = Class.forName(deviceClass).getConstructor((Class[]) null).newInstance();
                if (object instanceof Application) {
                    Device device = (Device) object;
                    add(device);
                    try {
                        device.initialise();
                    } catch (DeviceNotFoundException e) {}
                }
            } catch (IllegalArgumentException e) {
            } catch (SecurityException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            } catch (ClassNotFoundException e) {}
        }
    }*/

    /* Add / remove methods */
    protected void add(Application application) {
        applicationCycle.add(application);
    }

    protected boolean remove(Application application) {
        return applicationCycle.remove(application);
    }

    protected void add(Device device) {
        deviceList.add(device);
    }

    protected boolean remove(Device device) {
        return deviceList.remove(device);
    }

    public static void main(String[] args) {
        try {
            Main main = new Main();
            main.initialise();
            main.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}