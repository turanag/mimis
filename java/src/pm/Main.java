package pm;

import java.util.ArrayList;
import pm.application.example.ExampleApplication;
import pm.application.iTunes.iTunesApplication;
import pm.device.Device;
import pm.device.javainput.rumblepad.RumblepadDevice;
import pm.exception.action.TargetNotSetException;
import pm.listener.ActionListener;
import pm.listener.ActionProvider;
import pm.util.ArrayCycle;

public class Main extends ActionListener {
    ArrayCycle<Application> applicationCycle;
    ArrayList<Device> deviceList;

    boolean run;

    public Main() {
        super();
        applicationCycle = new ArrayCycle<Application>();
        deviceList = new ArrayList<Device>();
        ActionProvider.initialise(actionQueue);
    }

    public void start() throws Exception {
        //add(new ExampleDevice());
        add(new RumblepadDevice());
        //add(new Extreme3DDevice());
        //add(new JIntellitypeDevice());
        //add(new WiimoteDevice());
        for (Device device : deviceList) {
            device.start();
        }

        add(new ExampleApplication());
        add(new iTunesApplication());
        //add(new WinampApplication());
        applicationCycle.next();
        for (Application application : applicationCycle) {
            application.start();
        }
        run();
    }

    public void exit() {
        run = false;
        for (Device device : deviceList) {
            device.exit();
        }
        for (Application application : applicationCycle) {
            application.exit();
        }
        System.out.println("Main exit...");
    }

    protected void action(Action action) {
        try {
            System.out.println("Action: " + action + " Target: " + action.getTarget());
            switch (action.getTarget()) {
                case MAIN:
                    switch (action) {
                        case EXIT:
                            exit();
                            break;
                        default:
                            break;
                    }
                    break;
                case APPLICATION:
                    applicationCycle.current().add(action);
                    break;
                default:
                    //throw new UnknownTargetException();
            }
        } catch (TargetNotSetException e) {}
    }

    /* Add / remove methods */
    protected void add(Application application) {
        applicationCycle.add(application);
    }

    protected boolean remove(Application application) {
        return applicationCycle.remove(application);
    }

    protected void add(Device device) {
        deviceList.add(device);
    }

    protected boolean remove(Device device) {
        return deviceList.remove(device);
    }

    public static void main(String[] args) {
        try {
            new Main().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}