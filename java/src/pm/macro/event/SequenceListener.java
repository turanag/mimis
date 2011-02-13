package pm.macro.event;

import java.util.ArrayList;
import java.util.HashMap;

import pm.Task;
import pm.macro.Active;
import pm.macro.Event;
import pm.task.TaskGatherer;

public class SequenceListener {
    public ArrayList<Sequence> sequenceList;
    public HashMap<Sequence, Task> taskMap;
    public ArrayList<Active> activeList;

    public SequenceListener() {
        sequenceList = new ArrayList<Sequence>();
        taskMap = new HashMap<Sequence, Task>();
        activeList = new ArrayList<Active>();
    }

    public void add(Sequence sequence, Task task) {
        sequenceList.add(sequence);
        taskMap.put(sequence, task);
    }

    public void add(Event event) {
        for (Sequence sequence : sequenceList) {
            activeList.add(new Active(sequence));
        }
        ArrayList<Active> removeList = new ArrayList<Active>();
        for (Active active : activeList) {
            if (active.next(event)) {
                if (active.last()) {
                    Task task = taskMap.get(active.getSequence());
                    TaskGatherer.add(task);
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
