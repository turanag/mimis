package mimis;

import mimis.application.TestApplication;
import mimis.application.itunes.iTunesApplication;
import mimis.device.lirc.LircDevice;
import mimis.device.panel.PanelDevice;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.input.Feedback;
import mimis.input.Task;
import mimis.manager.ButtonManager;
import mimis.manager.Manager;
import mimis.parser.Parser;
import mimis.router.Router;
import mimis.util.ArrayCycle;
import mimis.value.Action;
import mimis.value.Target;
import mimis.worker.Component;

public class Main extends Component {
    protected TestApplication app;
    protected Manager manager;
    protected ButtonManager applicationManager, deviceManager;
    protected Gui gui;
    protected ArrayCycle<Component> componentCycle;

    public Main() {
        this.router = new Router();
    }

    public void activate() throws ActivateException {      
        /* Create gui from application and device managers */
        Component[] applicationArray = initialize(false, app = new TestApplication(), new iTunesApplication());
        applicationManager = new ButtonManager("Applications", applicationArray);
        deviceManager = new ButtonManager("Devices", initialize(false, new PanelDevice(), new LircDevice()));
        gui = new Gui(this, applicationManager, deviceManager);

        /* Create general manager */
        manager = new Manager(initialize(true, router, new Parser(), gui));

        /* Start managers */
        applicationManager.start();
        deviceManager.start();
        manager.start();

        /* Initialize component cycle */
        componentCycle = new ArrayCycle<Component>(applicationArray);

        listen(Task.class);
        super.activate();

        app.start();
        app.test();
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();

        log.debug("Stop managers");
        applicationManager.stop();
        deviceManager.stop();
        manager.stop();
    }

    public void exit() {
        super.exit();

        log.debug("Exit managers");
        applicationManager.exit();
        deviceManager.exit();
        manager.exit();
    }

    public Component[] initialize(boolean start, Component... componentArray) {
        for (Component component : componentArray) {
            component.setRouter(router);
            if (start) {
                component.start();
            }
        }
        return componentArray;
    }

    public void task(Task task) {
        if (task.getTarget().equals(Target.CURRENT)) {
            componentCycle.current().add(task);
        } else {
            super.task(task);
        }
    }

    public void end(Action action) {
        switch (action) {
            case NEXT:
                log.debug("Next component");
                route(new Feedback("Next component: " + componentCycle.next().getTitle()));
                break;
            case PREVIOUS:
                log.debug("Previous component");
                route(new Feedback("Previous component: " + componentCycle.previous().getTitle()));
                break;
            case EXIT:
                exit();
                break;
        }
    }

    public static void main(String[] args) {
        new Main().start(false);
    }
}
