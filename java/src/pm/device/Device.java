package pm.device;

import pm.Action;
import pm.Macro;
import pm.listener.ActionListener;
import pm.listener.MacroListener;

public abstract class Device extends ActionListener {
    protected MacroListener macroListener;

    public Device() {
        macroListener = new MacroListener();
    }

    public void add(Macro macro, Action action) {
        macroListener.add(macro, action);
    }

    public void start() {}
    public void exit() {}
}
