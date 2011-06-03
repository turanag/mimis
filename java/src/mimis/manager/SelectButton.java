package mimis.manager;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Action;
import javax.swing.JToggleButton;

import mimis.Worker;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Eventueel handigere knoppen gebruiken ivm terugtogglen
public class SelectButton<T extends Worker & Titled> extends JToggleButton implements ItemListener {
    protected Log log = LogFactory.getLog(getClass());

    protected static final long serialVersionUID = 1L;
    protected T activatable;
    protected Action action;

    public SelectButton(T activatable) {
        this.activatable = activatable;
        setText(activatable.title());
        setRolloverEnabled(false);
        addItemListener(this);
        //setFocusable(false);
        //getModel().setRollover(true);
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        //setSelected();
        int state = itemEvent.getStateChange();
        if (state == ItemEvent.SELECTED) {
            System.out.println("Selected");
            try {
                activatable.activate();
            } catch (ActivateException e) {
                // Het knopje moet worden terug getoggled
                log.error(e);
            }
        } else {
            System.out.println("Deselected");
            try {
                activatable.deactivate();
            } catch (DeactivateException e) {
                // Het knopje moet worden terug getoggled
                log.error(e);
            }
        }
    }

    public void setPressed(boolean pressed) {
        getModel().setPressed(pressed);
    }
}
