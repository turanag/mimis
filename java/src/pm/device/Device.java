package pm.device;

import pm.Action;
import pm.Macro;
import pm.exception.MacroException;
import pm.listener.ActionListener;
import pm.listener.MacroListener;
import pm.macro.Event;

public abstract class Device extends ActionListener {
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

    public void start() {}
    public void exit() {}
}
