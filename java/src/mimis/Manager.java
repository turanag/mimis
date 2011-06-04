package mimis;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

import mimis.manager.SelectButton;
import mimis.manager.Titled;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Manager<T extends Worker & Titled & Exitable> extends Worker {
    protected Log log = LogFactory.getLog(getClass());
    protected static final long serialVersionUID = 1L;
    protected static final int INTERVAL = 100;

    protected T[] manageableArray;
    protected Map<T, SelectButton<T>> buttonMap;

    public Manager(T[] manageableArray) {
        this.manageableArray = manageableArray;
        createButtons();
    }

    public void stop() {
        for (T manageable : manageableArray) {
            manageable.stop();
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

    protected JButton[] getButtons() {
        return buttonMap.values().toArray(new JButton[]{});
    }

    protected void work() {
        long before = Calendar.getInstance().getTimeInMillis();
        for (T manageable : manageableArray) {
            boolean active = manageable.active();
            //buttonMap.get(manageable).getModel().setArmed(active);

            buttonMap.get(manageable).getModel().setPressed(active);
        }
        long after = Calendar.getInstance().getTimeInMillis();
        int sleep = INTERVAL - (int) (after - before);
        sleep(sleep);
    }
}
