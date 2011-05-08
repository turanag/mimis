package pm;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.device.network.NetworkDevice;
import pm.device.wiimote.WiimoteDevice;
import pm.event.EventHandler;
import pm.event.EventSpreader;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;

public abstract class Manager extends EventHandler {
    protected Log log = LogFactory.getLog(Main.class);

    protected ArrayList<Device> deviceList;

    public Manager(EventSpreader eventSpreader) {
        EventHandler.initialise(eventSpreader);
        deviceList = new ArrayList<Device>();
        eventSpreader.start();
    }

    public void initialise() throws DeviceInitialiseException {
        //add(new JIntellitypeDevice());
        //add(new PlayerDevice());
        //add(new RumblepadDevice());
        add(new WiimoteDevice());
        //add(new GUIDevice());
        //add(new TextDevice());
        //add(new PanelDevice());
        //add(new LanTextDevice());
        //add(new Extreme3DDevice());
        //add(new NetworkDevice());
        startDevices();
    }

    public void exit() {
        exitDevices();
        stop();
    }

    protected void startDevices() {
        ArrayList<Device> removeList = new ArrayList<Device>();
        for (Device device : deviceList) {
            try {
                device.initialise();
                device.start();
                System.out.println("Device started: " + device);
            } catch (DeviceInitialiseException e) {
                removeList.add(device);
            }
        }
        for (Device device : removeList) {
            remove(device);
        }        
    }

    protected void exitDevices() {
        System.out.println("Exit devices...");
        for (Device device : deviceList) {
            try {
                device.exit();
            } catch (DeviceExitException e) {
                e.printStackTrace();
            }
        }
    }

    protected void add(Device device) {
        deviceList.add(device);
    }

    protected void remove(Device device) {
        deviceList.remove(device);
    }
}
