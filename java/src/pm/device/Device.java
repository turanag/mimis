package pm.device;

import pm.Action;
import pm.Macro;
import pm.exception.MacroException;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.listener.ActionProvider;
import pm.listener.MacroListener;
import pm.macro.Event;

public abstract class Device {
    protected MacroListener macroListener;

    public Device() {
        macroListener = new MacroListener();
    }

    public void add(Macro macro, Action action) {
        macroListener.add(macro, action);
    }

    public void add(Event event, Action action) throws MacroException {
        macroListener.add(event, action);
    }

    public void add(Event event) {
        macroListener.add(event);
    }

    public void add(Action action) {
        ActionProvider.add(action);
    }

    public void initialise() throws DeviceInitialiseException {}
    public void exit() throws DeviceExitException {}
}
