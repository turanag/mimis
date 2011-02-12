package pm.task;

import java.util.Queue;

import pm.Task;

public class TaskProvider {
    protected static Queue<Task> taskQueue;

    public static void initialise(Queue<Task> taskQueue) {
        TaskProvider.taskQueue = taskQueue;        
    }

    public static void add(Task task) {
        taskQueue.add(task);
    }
}
