package pm.device;

import pm.Action;
import pm.Macro;
import pm.action.ActionProvider;
import pm.exception.MacroException;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.Event;
import pm.macro.MacroListener;

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
