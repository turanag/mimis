package pm.macro;

import java.util.ArrayList;
import java.util.HashMap;

import pm.event.Task;
import pm.task.TaskListener;
import pm.task.TaskManager;

public class SequenceListener {
    protected TaskListener taskListener;
    protected ArrayList<Sequence> sequenceList;
    protected HashMap<Sequence, Task> taskMap;
    protected ArrayList<Active> activeList;

    public SequenceListener(TaskListener taskListener) {
        this.taskListener = taskListener;
        sequenceList = new ArrayList<Sequence>();
        taskMap = new HashMap<Sequence, Task>();
        activeList = new ArrayList<Active>();
    }

    public int add(Sequence sequence, Task task) {
        int id = sequenceList.size();
        sequenceList.add(sequence);
        taskMap.put(sequence, task);
        return id;
    }

    public void add(State state) {
        for (Sequence sequence : sequenceList) {
            activeList.add(new Active(sequence));
        }
        ArrayList<Active> removeList = new ArrayList<Active>();
        for (Active active : activeList) {
            if (active.next(state)) {
                if (active.last()) {
                    Task task = taskMap.get(active.getSequence());
                    TaskManager.add(taskListener, task);
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
