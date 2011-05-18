package pm;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.device.javainput.extreme3d.Extreme3DDevice;
import pm.device.javainput.rumblepad.RumblepadDevice;
import pm.device.jintellitype.JIntellitypeDevice;
import pm.device.network.NetworkDevice;
import pm.device.panel.PanelDevice;
import pm.device.player.PlayerDevice;
import pm.device.wiimote.WiimoteDevice;
import pm.event.EventHandler;
import pm.event.EventRouter;

public abstract class Manager extends EventHandler {
    protected Log log = LogFactory.getLog(getClass());

    protected ArrayList<Device> deviceList;

    public Manager(EventRouter eventRouter) {
        EventHandler.initialise(eventRouter);
        eventRouter.start();
    }

    public void start() {
        initialise();
        Device[] deviceArray = new Device[] {
            new WiimoteDevice(),
            new PanelDevice(),
            new JIntellitypeDevice(),
            new PlayerDevice(),
            new RumblepadDevice(),
            new Extreme3DDevice(),
            new NetworkDevice()};
    }

    public void exit() {
        log.debug("Exit devices...");
        for (Device device : deviceList) {
            device.exit();
        }
        stop();
    }
}
