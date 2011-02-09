package pm.listener;

import java.util.ArrayList;
import java.util.HashMap;

import pm.Action;
import pm.Macro;
import pm.exception.MacroException;
import pm.macro.Active;
import pm.macro.Event;

public class MacroListener extends ActionListener {
    public ArrayList<Macro> macroList;
    public HashMap<Macro, Action> actionMap;
    public ArrayList<Active> activeList;

    public MacroListener() {
        macroList = new ArrayList<Macro>();
        actionMap = new HashMap<Macro, Action>();
        activeList = new ArrayList<Active>();
    }

    public void add(Macro macro, Action action) {
        macroList.add(macro);
        actionMap.put(macro, action);
    }

    public void add(Event event, Action action) throws MacroException {
        add(new Macro(event), action);
    }

    public void add(Event event) {
        System.out.println("]] binnen " + event);
        for (Macro macro : macroList) {
            activeList.add(new Active(macro));
        }
        ArrayList<Active> removeList = new ArrayList<Active>();
        for (Active active : activeList) {
            if (active.next(event)) {
                if (active.last()) {
                    add(actionMap.get(active.getMacro()));
                    removeList.add(active);
                }
            } else {
                removeList.add(active);
            }
        }
        for (Active active : removeList) {
            activeList.remove(active);
        }
    }
}
