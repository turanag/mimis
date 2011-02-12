package pm.task;

import java.util.Queue;

import pm.Task;

public class TaskGatherer {
    protected static Queue<Task> taskQueue;

    public static void initialise(Queue<Task> taskQueue) {
        TaskGatherer.taskQueue = taskQueue;        
    }

    public static void add(Task task) {
        if (task instanceof Stopper) {
            Stopper stopper = (Stopper) task;
            stopper.stop();
        } else {
            taskQueue.add(task);
        }
    }
}
