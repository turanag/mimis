package mimis;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JToggleButton;

import mimis.exception.worker.DeactivateException;
import mimis.manager.ManageButton;
import mimis.manager.Titled;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Manager<T extends Worker & Titled> extends Worker {
    protected Log log = LogFactory.getLog(getClass());
    protected static final long serialVersionUID = 1L;
    protected static final int INTERVAL = 1000;

    protected T[] manageableArray;
    protected Map<T, ManageButton<T>> buttonMap;

    public Manager(T[] manageableArray) {
        this.manageableArray = manageableArray;
        createButtons();
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        for (T manageable : manageableArray) {
            manageable.stop();
        }
    }

    public void exit() {
        super.exit();
        for (T manageable : manageableArray) {
            manageable.exit();
        }
    }

    public int count() {
        return manageableArray.length;
    }

    protected void createButtons() {
        buttonMap = new HashMap<T, ManageButton<T>>();
        for (T manageable : manageableArray) {
            ManageButton<T> button = new ManageButton<T>(manageable);
            buttonMap.put(manageable, button);
        }
    }

    protected JToggleButton[] getButtons() {
        return buttonMap.values().toArray(new JToggleButton[]{});
    }

    protected void work() {
        /* Todo: timertask! */
        long before = Calendar.getInstance().getTimeInMillis();
        for (T manageable : manageableArray) {
            boolean active = manageable.active();
            ManageButton<T> button = buttonMap.get(manageable);
            button.setPressed(active);
        }
        long after = Calendar.getInstance().getTimeInMillis();
        int sleep = INTERVAL - (int) (after - before);
        sleep(sleep);
    }
}