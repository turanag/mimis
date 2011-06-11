package mimis;

import mimis.device.javainput.extreme3d.Extreme3DDevice;
import mimis.device.javainput.rumblepad.RumblepadDevice;
import mimis.device.jintellitype.JIntellitypeDevice;
import mimis.device.lirc.LircDevice;
import mimis.device.network.NetworkDevice;
import mimis.device.panel.PanelDevice;
import mimis.device.wiimote.WiimoteDevice;
import mimis.event.EventRouter;
import mimis.event.router.GlobalRouter;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.util.swing.Dialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Client {
    protected Log log = LogFactory.getLog(getClass());

    public static final String IP = "127.0.0.1";
    public static final int PORT = 6789;

    protected EventRouter eventRouter;
    protected Device[] deviceArray;

    public Client(String ip, int port) {
        eventRouter = new GlobalRouter(ip, port);
        deviceArray = new Device[] {
            new LircDevice(),
            new WiimoteDevice(),
            new PanelDevice(),
            new JIntellitypeDevice(),
            new RumblepadDevice(),
            new Extreme3DDevice(),
            new NetworkDevice()};
    }

    public void start() {
        log.debug("Client");
        Mimis mimis = new Mimis(eventRouter, deviceArray);
        try {
            mimis.activate();
        } catch (ActivateException e) {
            log.fatal(e);
        }
        try {
            mimis.stop();
        } catch (DeactivateException e) {
            log.fatal(e);
        }
    }

    public static void main(String[] args) {
        String ip = Dialog.question("Server IP:", IP);
        int port = Integer.valueOf(Dialog.question("Server Port:", PORT));
        new Client(ip, port).start();
    }
}
