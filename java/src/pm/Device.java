package pm;

import pm.exception.MacroException;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.Event;
import pm.macro.MacroListener;
import pm.task.TaskProvider;

public abstract class Device {
    protected MacroListener macroListener;

    public Device() {
        macroListener = new MacroListener();
    }

    public void add(Macro macro, Task task) {
        macroListener.add(macro, task);
    }

    public void add(Event event, Task task) throws MacroException {
        macroListener.add(event, task);
    }

    public void add(Event event) {
        macroListener.add(event);
    }

    public void add(Task task) {
        TaskProvider.add(task);
    }

    public void initialise() throws DeviceInitialiseException {}
    public void exit() throws DeviceExitException {}
}
