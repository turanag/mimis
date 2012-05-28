package mimis.manager;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JToggleButton;

import mimis.worker.Worker;

public class ButtonManager extends Manager {
    protected static final String TITLE = "Workers";
    
    protected String title;
    protected Map<Worker, WorkerButton> buttonMap;

    public ButtonManager(Worker... workerArray) {
        this(TITLE, workerArray);
    }

    public ButtonManager(String title, Worker... workerArray) {
        this.workerArray = workerArray;
        this.title = title;
        createButtons();
    }

    public String getTitle() {
        return title;
    }
    
    public JToggleButton[] getButtons() {
        return buttonMap.values().toArray(new JToggleButton[]{});
    }

    protected void createButtons() {
        buttonMap = new HashMap<Worker, WorkerButton>();
        for (Worker worker : workerArray) {
            WorkerButton button = new WorkerButton(worker);
            buttonMap.put(worker, button);
        }
    }

    protected void work() {
        long before = Calendar.getInstance().getTimeInMillis();
        for (Worker worker : workerArray) {
            buttonMap.get(worker).setPressed(worker.active());
        }
        long after = Calendar.getInstance().getTimeInMillis();
        int sleep = INTERVAL - (int) (after - before);
        sleep(sleep);
    }
}
