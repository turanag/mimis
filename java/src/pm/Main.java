package pm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.application.ApplicationSelector;
import pm.application.cmd.windows.gomplayer.GomPlayerApplication;
import pm.application.cmd.windows.winamp.WinampApplication;
import pm.application.cmd.windows.wmp.WMPApplication;
import pm.application.itunes.iTunesApplication;
import pm.application.mpc.MPCApplication;
import pm.application.vlc.VLCApplication;
import pm.event.spreader.LocalSpreader;
import pm.util.ArrayCycle;
import pm.value.Action;

public class Main extends Manager {
    protected Log log = LogFactory.getLog(getClass());
    protected ArrayCycle<Application> applicationCycle;
    protected ApplicationSelector applicationSelector;

    public Main() {
        super(new LocalSpreader());
    }

    protected void action(Action action) {
        log.debug(String.format("action(%s)", action));
        switch (action) {
            case NEXT:
                eventSpreader.set(applicationCycle.next());
                System.out.println(applicationCycle.current());
                break;
            case PREVIOUS:
                eventSpreader.set(applicationCycle.previous());
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
        applicationSelector = new ApplicationSelector(applicationArray);
        applicationCycle = new ArrayCycle<Application>(applicationArray);
        eventSpreader.set(applicationCycle.current());
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
