package mimis;

import mimis.event.EventHandler;
import mimis.event.EventRouter;
import mimis.event.Feedback;
import mimis.exception.worker.ActivateException;
import mimis.feedback.TextFeedback;
import mimis.sequence.SequenceListener;
import mimis.util.ArrayCycle;
import mimis.value.Action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Mimis extends EventHandler {
    protected Log log = LogFactory.getLog(getClass());

    protected ArrayCycle<Application> applicationCycle;
    protected Device[] deviceArray;
    protected Application[] applicationArray;
    protected GUI gui;

    protected Manager<Application> applicationManager;
    protected Manager<Device> deviceManager;

    public Mimis(EventRouter eventRouter) {
        this(eventRouter, new Application[0], new Device[0]);
    }

    public Mimis(EventRouter eventRouter, Application[] applicationArray) {
        this(eventRouter, applicationArray, new Device[0]);
    }

    public Mimis(EventRouter eventRouter, Device[] deviceArray) {
        this(eventRouter, new Application[0], deviceArray);
    }

    public Mimis(EventRouter eventRouter, Application[] applicationArray, Device[] deviceArray) {
        this.applicationArray = applicationArray;
        this.deviceArray = deviceArray;
        applicationCycle = new ArrayCycle<Application>(applicationArray);

        log.debug("Initialise EventHandler and SequenceListener");
        EventHandler.initialise(eventRouter);
        SequenceListener.initialise(eventRouter);

        log.debug("Add EventListeners to EventRouter");
        eventRouter.add(this);
        eventRouter.add(applicationArray);
        eventRouter.add(deviceArray);

        log.debug("Create managers");
        applicationManager = new Manager<Application>(applicationArray);
        deviceManager = new Manager<Device>(deviceArray);
    }

    public void activate() throws ActivateException {
        log.debug("Activate event router");
        eventRouter.activate();

        log.debug("Activate managers");
        applicationManager.activate();
        deviceManager.activate();

        log.debug("Create gui");
        gui = new GUI(this, applicationManager, deviceManager);

        if (applicationCycle.size() > 0) {
            log.debug("Initialise application cycle");
            eventRouter.set(applicationCycle.current());
        }
        super.activate(false);
    }

    public void stop() {
        log.debug("Stop event router");
        eventRouter.stop();

        log.debug("Stop managers");
        applicationManager.stop();
        deviceManager.stop();
        super.stop();
    }

    protected void action(Action action) {
        log.debug(String.format("action(%s)", action));
        switch (action) {
            case NEXT:
                eventRouter.set(applicationCycle.next());
                add(new TextFeedback("Next application: " + applicationCycle.current().title()));
                break;
            case PREVIOUS:
                eventRouter.set(applicationCycle.previous());
                add(new TextFeedback("Previous application: " + applicationCycle.current().title()));
                break;
            case EXIT:
                stop();
                break;
        }
    }

    protected void feedback(Feedback feedback) {
        if (feedback instanceof TextFeedback) {
            gui.writeLine(((TextFeedback) feedback).getText());
        }
    }
}
