package mimis;

import mimis.device.javainput.extreme3d.Extreme3DDevice;
import mimis.device.javainput.rumblepad.RumblepadDevice;
import mimis.device.jintellitype.JIntellitypeDevice;
import mimis.device.lirc.LircDevice;
import mimis.device.network.NetworkDevice;
import mimis.device.panel.PanelDevice;
import mimis.device.player.PlayerDevice;
import mimis.device.wiimote.WiimoteDevice;
import mimis.event.EventRouter;
import mimis.event.router.GlobalRouter;
import mimis.exception.event.router.GlobalRouterException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


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
            new LircDevice(),
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
