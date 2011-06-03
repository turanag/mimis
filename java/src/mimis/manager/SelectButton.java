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

public class SelectButton<T extends Worker & Titled> extends JToggleButton implements ItemListener {
    protected Log log = LogFactory.getLog(getClass());

    protected static final long serialVersionUID = 1L;
    protected T activatable;
    protected Action action;

    public SelectButton(T activatable) {
        this.activatable = activatable;
        setText(activatable.title());
        addItemListener(this);
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        int state = itemEvent.getStateChange();
        if (state == ItemEvent.SELECTED) {
            log.trace("Selected: " + activatable.title());
            setPressed(false);
            try {
                activatable.activate();
            } catch (ActivateException e) {
                log.error(e);
            }
        } else {
            log.trace("Deselected: " + activatable.title());
            setPressed(true);
            try {
                activatable.deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        }
    }

    public void setPressed(boolean pressed) {
        getModel().setPressed(pressed);
    }
}
