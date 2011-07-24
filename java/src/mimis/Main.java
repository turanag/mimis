package mimis;

import mimis.application.cmd.windows.gomplayer.GomPlayerApplication;
import mimis.application.cmd.windows.winamp.WinampApplication;
import mimis.application.cmd.windows.wmp.WMPApplication;
import mimis.application.itunes.iTunesApplication;
import mimis.application.lirc.ipod.iPodApplication;
import mimis.application.mpc.MPCApplication;
import mimis.application.vlc.VLCApplication;
import mimis.device.javainput.extreme3d.Extreme3DDevice;
import mimis.device.javainput.rumblepad.RumblepadDevice;
import mimis.device.jintellitype.JIntellitypeDevice;
import mimis.device.lirc.LircDevice;
import mimis.device.network.NetworkDevice;
import mimis.device.panel.PanelDevice;
import mimis.device.wiimote.WiimoteDevice;
import mimis.event.EventRouter;
import mimis.event.router.LocalRouter;
import mimis.exception.worker.ActivateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {
    protected Log log = LogFactory.getLog(getClass());

    protected EventRouter eventRouter;
    protected Application[] applicationArray;
    protected Device[] deviceArray;

    public Main() {
        eventRouter = new LocalRouter();        
        applicationArray = new Application[] {
            new iTunesApplication(),
            new GomPlayerApplication(),
            new WMPApplication(),
            new MPCApplication(),
            new VLCApplication(),
            new WinampApplication(),
            new iPodApplication()};
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
        log.debug("Main");
        Mimis mimis = new Mimis(eventRouter, applicationArray, deviceArray);
        try {
            mimis.activate();
        } catch (ActivateException e) {
            log.fatal(e);
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
