package pm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.application.cmd.windows.gomplayer.GomPlayerApplication;
import pm.application.cmd.windows.winamp.WinampApplication;
import pm.application.cmd.windows.wmp.WMPApplication;
import pm.application.itunes.iTunesApplication;
import pm.application.mpc.MPCApplication;
import pm.application.vlc.VLCApplication;
import pm.device.javainput.extreme3d.Extreme3DDevice;
import pm.device.javainput.rumblepad.RumblepadDevice;
import pm.device.jintellitype.JIntellitypeDevice;
import pm.device.network.NetworkDevice;
import pm.device.panel.PanelDevice;
import pm.device.player.PlayerDevice;
import pm.device.wiimote.WiimoteDevice;
import pm.event.EventRouter;
import pm.event.router.LocalRouter;

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
            new WinampApplication()};
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
        log.debug("Main");
        Mimis mimis = new Mimis(eventRouter, applicationArray, deviceArray);
        mimis.start();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
