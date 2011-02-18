package pm.task;

import java.util.ArrayList;

import pm.Application;
import pm.Device;
import pm.Main;
import pm.Target;
import pm.Task;
import pm.application.ApplicationCycle;

public class TaskGatherer {
    protected static ArrayList<TaskListener> taskListenerList;
    protected static ApplicationCycle applicationCycle;
    
    public static void initialise(ApplicationCycle applicationCycle) {
        taskListenerList = new ArrayList<TaskListener>();
        TaskGatherer.applicationCycle = applicationCycle;
    }

    public static void add(Application application) {
        add(application);
        applicationCycle.add(application);
    }
    
    public static void add(TaskListener taskListner) {
        taskListenerList.add(taskListner);
    }
    
    public static void add(Task task) {
        if (task instanceof Stopper) {
            Stopper stopper = (Stopper) task;
            stopper.stop();
        } else {
            Target target = task.getTarget();
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
                    case APPLICATION:
                        applicationCycle.current().add(task);
                }
            }
        }
    }
}