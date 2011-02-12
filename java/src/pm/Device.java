package pm;

import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.Event;
import pm.macro.MacroListener;
import pm.macro.event.Hold;
import pm.macro.event.Press;
import pm.macro.event.Release;
import pm.task.Continuous;
import pm.task.Stopper;

public abstract class Device {
    protected MacroListener macroListener;

    public Device() {
        macroListener = new MacroListener();
    }

    /* Register macro's */
    protected void add(Macro macro, Task task) {
        macroListener.add(macro, task);
    }

    protected void add(Event event, Task task) {
        add(new Macro(event), task);
    }

    protected void add(Macro startMacro, Macro stopMacro, Continuous continuous) {
        add(startMacro, continuous);
        add(stopMacro, new Stopper(continuous));        
    }

    protected void add(Event startEvent, Event stopEvent, Continuous continuous) {
        add(startEvent, continuous);
        add(stopEvent, new Stopper(continuous));        
    }

    protected void add(Hold hold, Continuous continuous) {
        Button button = hold.getButton();
        add(new Press(button), new Release(button), continuous);
    }

    /* Recognize events */
    protected void add(Event event) {
        macroListener.add(event);
    }

    /* Device default methods */
    public void initialise() throws DeviceInitialiseException {}
    public void exit() throws DeviceExitException {}
}
