package pm;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.application.ApplicationCycle;
import pm.application.cmd.windows.gomplayer.GomPlayerApplication;
import pm.application.cmd.windows.wmp.WMPApplication;
import pm.application.example.ExampleApplication;
import pm.application.itunes.iTunesApplication;
import pm.application.mpc.MPCApplication;
import pm.application.vlc.VLCApplication;
import pm.application.windows.winamp.WinampApplication;
import pm.device.gui.GUIDevice;
import pm.device.javainput.extreme3d.Extreme3DDevice;
import pm.device.javainput.rumblepad.RumblepadDevice;
import pm.device.jintellitype.JIntellitypeDevice;
import pm.device.panel.PanelDevice;
import pm.device.player.PlayerDevice;
import pm.device.text.TextDevice;
import pm.device.text.lan.LanTextDevice;
import pm.device.wiimote.WiimoteDevice;
import pm.event.EventListener;
import pm.event.EventManager;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.Active;
import pm.value.Action;

public class Main extends EventListener {
    protected Log log = LogFactory.getLog(Main.class);

    //protected String[] deviceClassArray;
    protected ApplicationCycle applicationCycle;
    protected ArrayList<Device> deviceList;
   
    public Main() {
        super();
        /*deviceClassArray = new String[] {
            "pm.device.jintellitype.JIntellitypeDevice",
            "pm.device.javainput.rumblepad.RumblepadDevice",
            "pm.device.javainput.extreme3d.Extreme3DDevice",
            "pm.device.wiimote.WiimoteDevice"};*/
        applicationCycle = new ApplicationCycle();
        deviceList = new ArrayList<Device>();
        EventManager.initialise(applicationCycle);
        EventManager.add(this);
    }

    public void initialise() throws DeviceInitialiseException {
        add(new JIntellitypeDevice());
        //add(new PlayerDevice());
        //add(new RumblepadDevice());
        add(new WiimoteDevice());
        //add(new GUIDevice());
        //add(new TextDevice());
        //add(new PanelDevice());
        //add(new LanTextDevice());
        //add(new Extreme3DDevice());
        startDevices();

        //add(new ExampleApplication());
        //add(new WMPApplication());
        //add(new GomPlayerApplication());
        //add(new WinampApplication());
        add(new iTunesApplication());
        //add(new VLCApplication());
        //add(new MPCApplication());
        startApplications();
    }

    protected void startApplications() {
        ArrayList<Application> removeList = new ArrayList<Application>();
        for (Application application : applicationCycle) {
            try {
                application.initialise();
                application.start();
            } catch (ApplicationInitialiseException e) {
                removeList.add(application);
            }
        }
        for (Application application : removeList) {
            remove(application);
        }        
    }

    protected void startDevices() {
        ArrayList<Device> removeList = new ArrayList<Device>();
        for (Device device : deviceList) {
            try {
                device.initialise();
                device.start();
                log.info("Device started: " + device);
            } catch (DeviceInitialiseException e) {
                removeList.add(device);
            }
        }
        for (Device device : removeList) {
            remove(device);
        }        
    }

    public void exit() {
        System.out.println("Exit devices...");
        for (Device device : deviceList) {
            try {
                device.exit();
            } catch (DeviceExitException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Exit applications...");
        for (Application application : applicationCycle) {
            try {
                application.exit();
            } catch (ApplicationExitException e) {
                e.printStackTrace();
            }
        }        
        System.out.println("Exit main...");
        stop();
    }

    protected void action(Action action) {
        System.out.println("Main: " + action);
        switch (action) {
            case NEXT:
                applicationCycle.next();
                System.out.println(applicationCycle.current());
                break;
            case PREVIOUS:
                applicationCycle.previous();
                System.out.println(applicationCycle.current());
                break;
            case EXIT:
                exit();
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
        EventManager.add(application);
        applicationCycle.add(application);
    }

    protected void remove(Application application) {
        EventManager.remove(application);
        applicationCycle.remove(application);
    }

    protected void add(Device device) {
        EventManager.add(device);
        deviceList.add(device);
    }

    protected void remove(Device device) {
        EventManager.remove(device);
        deviceList.remove(device);
    }

    public static void main(String[] args) {
        try {
            Main main = new Main();
            main.initialise();
            main.start(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}