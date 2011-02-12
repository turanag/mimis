package pm;

import java.util.ArrayList;

import pm.action.ActionListener;
import pm.action.ActionProvider;
import pm.application.Winamp.WinampApplication;
import pm.application.iTunes.iTunesApplication;
import pm.device.Device;
import pm.device.gui.GUIDevice;
import pm.device.jintellitype.JIntellitypeDevice;
import pm.exception.action.TargetNotSetException;
import pm.exception.application.ApplicationExitException;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.util.ArrayCycle;

public class Main extends ActionListener {
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
        ActionProvider.initialise(actionQueue);
    }

    public void initialise() throws DeviceInitialiseException {
        add(new JIntellitypeDevice());
        //add(new RumblepadDevice());
        add(new GUIDevice());
        for (Device device : deviceList) {
            device.initialise();
        }
        //add(new ExampleApplication());
        //add(new WinampApplication());
        add(new iTunesApplication());
        
        //applicationCycle.next();
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
        try {
            System.out.println("Action: " + action + " Target: " + action.getTarget());
            switch (action.getTarget()) {
                case MAIN:
                    switch (action) {
                        case EXIT:
                            exit();
                            break;
                        default:
                            break;
                    }
                    break;
                case APPLICATION:
                    applicationCycle.current().add(action);
                    break;
                default:
                    //throw new UnknownTargetException();
            }
        } catch (TargetNotSetException e) {}
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