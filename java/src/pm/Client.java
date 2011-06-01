package pm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.device.javainput.extreme3d.Extreme3DDevice;
import pm.device.javainput.rumblepad.RumblepadDevice;
import pm.device.jintellitype.JIntellitypeDevice;
import pm.device.network.NetworkDevice;
import pm.device.panel.PanelDevice;
import pm.device.player.PlayerDevice;
import pm.device.wiimote.WiimoteDevice;
import pm.event.EventRouter;
import pm.event.router.GlobalRouter;
import pm.exception.event.router.GlobalRouterException;

public class Client {
    protected Log log = LogFactory.getLog(getClass());

    public static final String IP = "127.0.0.1";
    public static final int PORT = 6789;

    protected EventRouter eventRouter;
    protected Device[] deviceArray;

    public Client() throws GlobalRouterException {
        this(IP, PORT);
    }

    public Client(String ip, int port) throws GlobalRouterException {
        eventRouter = new GlobalRouter(ip, port);
        deviceArray = new Device[] {
            new WiimoteDevice(),
            new PanelDevice(),
            new JIntellitypeDevice(),
            new PlayerDevice(),
            new RumblepadDevice(),
            new Extreme3DDevice(),
            new NetworkDevice()};
    }

    public void start() {
        log.debug("Client");
        Mimis mimis = new Mimis(eventRouter, deviceArray);
        mimis.start();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
