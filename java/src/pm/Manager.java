package pm;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JToggleButton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.manager.SelectButton;
import pm.manager.Titled;

public class Manager<T extends Worker & Titled & Exitable> extends Worker {
    protected Log log = LogFactory.getLog(getClass());
    protected static final long serialVersionUID = 1L;
    protected static final int INTERVAL = 5000;

    protected T[] manageableArray;
    protected Map<T, SelectButton<T>> buttonMap;
    
    public Manager(String title) {
        log.debug("Manager constructed");
    }

    public Manager(T[] manageableArray) {
        this.manageableArray = manageableArray;
        createButtons();
    }

    public void stop() {
        super.stop();
        for (T manageable : manageableArray) {
            manageable.exit();
        }
        super.stop();
    }

    protected void createButtons() {
        buttonMap = new HashMap<T, SelectButton<T>>();
        for (T manageable : manageableArray) {
            SelectButton<T> button = new SelectButton<T>(manageable);
            buttonMap.put(manageable, button);
        }
    }

    protected JToggleButton[] getButtons() {
        return buttonMap.values().toArray(new JToggleButton[]{});
    }

    protected void work() {
        long before = Calendar.getInstance().getTimeInMillis();
        for (T manageable : manageableArray) {
            boolean active = manageable.active();
            buttonMap.get(manageable).setPressed(active);
        }
        long after = Calendar.getInstance().getTimeInMillis();
        int sleep = INTERVAL - (int) (after - before);
        sleep(sleep);
    }
}
