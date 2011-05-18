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
import pm.event.router.LocalRouter;
import pm.util.ArrayCycle;
import pm.value.Action;

public class Main extends Manager {
    protected Log log = LogFactory.getLog(getClass());
    protected ArrayCycle<Application> applicationCycle;

    public Main() {
        super(new LocalRouter());
    }

    protected void action(Action action) {
        log.debug(String.format("action(%s)", action));
        switch (action) {
            case NEXT:
                eventRouter.set(applicationCycle.next());
                System.out.println(applicationCycle.current());
                break;
            case PREVIOUS:
                eventRouter.set(applicationCycle.previous());
                System.out.println(applicationCycle.current());
                break;
            case EXIT:
                exit();
                break;
        }
    }

    public void start() {
        super.start();
        Application[] applicationArray = new Application[] {
            new iTunesApplication(),
            new GomPlayerApplication(),
            new WMPApplication(),
            new MPCApplication(),
            new VLCApplication(),
            new WinampApplication()};
        applicationCycle = new ArrayCycle<Application>(applicationArray);
        Device[] deviceArray = new Device[] {
                new WiimoteDevice(),
                new PanelDevice(),
                new JIntellitypeDevice(),
                new PlayerDevice(),
                new RumblepadDevice(),
                new Extreme3DDevice(),
                new NetworkDevice()};
        GUI gui = new GUI(applicationArray, deviceArray);
        eventRouter.set(applicationCycle.current());
        super.start(false);
    }

    public void exit() {
        System.out.println("Exit applications...");
        for (Application application : applicationCycle) {
            application.exit();
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
