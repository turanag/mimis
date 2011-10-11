package mimis;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JToggleButton;

import mimis.exception.worker.DeactivateException;
import mimis.manager.WorkerButton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Manager<T extends Worker> extends Worker {
    protected Log log = LogFactory.getLog(getClass());
    protected static final long serialVersionUID = 1L;
    protected static final int INTERVAL = 1000;

    protected Worker[] workerArray;
    protected Map<Worker, WorkerButton> buttonMap;

    public Manager(T[] workerArray) {
        this.workerArray = workerArray;
        createButtons();
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        for (Worker manageable : workerArray) {
            manageable.stop();
        }
    }

    public void exit() {
        super.exit();
        for (Worker manageable : workerArray) {
            manageable.exit();
        }
    }

    public int count() {
        return workerArray.length;
    }

    protected void createButtons() {
        buttonMap = new HashMap<Worker, WorkerButton>();
        for (Worker manageable : workerArray) {
            WorkerButton button = new WorkerButton(manageable);
            buttonMap.put(manageable, button);
        }
    }

    protected JToggleButton[] getButtons() {
        return buttonMap.values().toArray(new JToggleButton[]{});
    }

    protected void work() {
        long before = Calendar.getInstance().getTimeInMillis();
        for (Worker manageable : workerArray) {
            boolean active = manageable.active();
            WorkerButton button = buttonMap.get(manageable);
            button.setPressed(active);
        }
        long after = Calendar.getInstance().getTimeInMillis();
        int sleep = INTERVAL - (int) (after - before);
        sleep(sleep);
    }
}