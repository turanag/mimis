package pm;

import java.util.ArrayList;

import pm.event.spreader.LocalSpreader;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.exception.device.DeviceInitialiseException;
import pm.util.ArrayCycle;
import pm.value.Action;

public class Main extends Manager {
    protected ArrayCycle<Application> applicationCycle;
    protected ApplicationSelector applicationSelector;
    
    public Main() {
        super(new LocalSpreader());
        applicationCycle = new ArrayCycle<Application>();
        applicationSelector = new ApplicationSelector(applicationCycle);
    }

    protected void action(Action action) {
        System.out.println("Manager: " + action);
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

    public void initialise() throws DeviceInitialiseException {
        super.initialise();
        //add(new iTunesApplication());
        //log.error("main init");
        //startApplications();
    }

    public void exit() {
        exitDevices();
        stop();
    }

    /*protected void startApplications() {
        ArrayList<Application> removeList = new ArrayList<Application>();
        for (Application application : applicationCycle) {
            try {
                application.initialise();
                application.start();
                log.debug(application);
            } catch (ApplicationInitialiseException e) {
                removeList.add(application);
            }
        }
        for (Application application : removeList) {
            remove(application);
        }
        eventSpreader.set(applicationCycle.current());        
    }*/

    /*protected void exitApplications() {
        System.out.println("Exit applications...");
        for (Application application : applicationCycle) {
            try {
                application.exit();
            } catch (ApplicationExitException e) {}
        }        
        System.out.println("Exit main...");
    }*/

    /*protected void add(Application application) {
        applicationCycle.add(application);
    }*/

    /*protected void remove(Application application) {
        applicationCycle.remove(application);
    }*/

    public void start() {
        log.info("LocalManager!");
        try {
            initialise();
        } catch (DeviceInitialiseException e) {}
        super.start(false);
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
