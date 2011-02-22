package pm.task;

import java.util.ArrayList;

import pm.Application;
import pm.Device;
import pm.Main;
import pm.Task;
import pm.application.ApplicationCycle;
import pm.value.Target;

public class TaskManager {
    protected static ArrayList<TaskListener> taskListenerList;
    protected static ApplicationCycle applicationCycle;

    public static void initialise(ApplicationCycle applicationCycle) {
        taskListenerList = new ArrayList<TaskListener>();
        TaskManager.applicationCycle = applicationCycle;
    }

    public static void add(TaskListener taskListener) {
        taskListenerList.add(taskListener);
    }

    public static void add(Task task) {
        if (task instanceof Stopper) {
            Stopper stopper = (Stopper) task;
            stopper.stop();
        } else {
            Target target = task.getTarget();
            if (target.equals(Target.APPLICATION)) {
                applicationCycle.current().add(task);
            } else {
                for (TaskListener taskListener : taskListenerList) {
                    switch (target) {
                        case ALL:
                            taskListener.add(task);
                        case MAIN:
                            if (taskListener instanceof Main) {
                                taskListener.add(task);
                            } 
                            break;
                        case DEVICES:
                            if (taskListener instanceof Device) {
                                taskListener.add(task);
                            } 
                            break;
                        case APPLICATIONS:
                            if (taskListener instanceof Application) {
                                taskListener.add(task);
                            } 
                            break;
                    }
                }
            }
        }
    }
}