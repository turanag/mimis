package pm.macro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

import pm.Macro;

import pm.action.Actions;
import pm.action.Targets;

public class MacroListener extends Thread {
    protected static Queue<Actions> actionQueue;

    public ArrayList<Macro> macroList;
    public HashMap<Macro, Actions> actionMap;
    public ArrayList<Active> activeList;
    
    public MacroListener() {
        macroList = new ArrayList<Macro>();
        actionMap = new HashMap<Macro, Actions>();
        activeList = new ArrayList<Active>();
    }

    public void add(Macro macro, Actions action, Targets target) {
        action.setTarget(target); // Todo: target en action duidelijker integreren / hernoemen.
        macroList.add(macro);
        actionMap.put(macro, action);
    }

    public void add(Event event) {
        for (Macro macro : macroList) {
            activeList.add(new Active(macro));
        }
        ArrayList<Active> removeList = new ArrayList<Active>();
        for (Active active : activeList) {
            if (active.next(event)) {
                if (active.last()) {
                    actionQueue.add(actionMap.get(active.getMacro())); // Todo: dit indirect doen?
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

    public static void initialise(Queue<Actions> actionQueue) {
        MacroListener.actionQueue = actionQueue;        
    }
}
