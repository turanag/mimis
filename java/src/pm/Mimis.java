package pm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.event.EventHandler;
import pm.event.EventRouter;
import pm.util.ArrayCycle;
import pm.value.Action;

public class Mimis extends EventHandler {
    protected Log log = LogFactory.getLog(getClass());

    protected ArrayCycle<Application> applicationCycle;
    protected Device[] deviceArray;
    protected Application[] applicationArray;
    protected GUI gui;

    protected Manager<Application> applicationManager;
    protected Manager<Device> deviceManager;
    
    public Mimis(EventRouter eventRouter) {
        this(eventRouter, new Application[] {}, new Device[] {});
    }
    
    public Mimis(EventRouter eventRouter, Application[] applicationArray) {
        this(eventRouter, applicationArray, new Device[] {});
    }

    public Mimis(EventRouter eventRouter, Device[] deviceArray) {
        this(eventRouter, new Application[] {}, deviceArray);
    }

    public Mimis(EventRouter eventRouter, Application[] applicationArray, Device[] deviceArray) {
        EventHandler.initialise(eventRouter);
        applicationManager = new Manager<Application>(applicationArray);
        deviceManager = new Manager<Device>(deviceArray);
        
        this.applicationArray = applicationArray;
        this.deviceArray = deviceArray;
        applicationCycle = new ArrayCycle<Application>(applicationArray);
    }

    public void start() {
        log.debug("Start managers");
        applicationManager.start();
        deviceManager.start();
    
        log.debug("Create gui");
        gui = new GUI(this, applicationManager, deviceManager);
    
        if (applicationCycle.size() > 0) {
            log.debug("Initialise application cycle");
            eventRouter.set(applicationCycle.current());
        }
        super.start(false);
    }

    public void exit() {
        log.debug("Stop event router");
        eventRouter.stop();
        
        log.debug("Stop managers");
        applicationManager.stop();
        deviceManager.stop();        

        stop();
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
}
