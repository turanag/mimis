package mimis.manager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.JToggleButton;

import mimis.Worker;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ManageButton<T extends Worker & Titled> extends JToggleButton implements MouseListener {
    protected Log log = LogFactory.getLog(getClass());

    protected static final long serialVersionUID = 1L;
    protected T manageable;
    protected Action action;

    public ManageButton(T manageable) {
        this.manageable = manageable;
        setText(manageable.title());
        setFocusable(false);
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent event) {
        if (manageable.active()) {
            try {
                log.trace("Uit");
                manageable.deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        } else {
            try {
                log.trace("Aan");
                manageable.activate();
            } catch (ActivateException e) {
                log.error(e);
            }
        }        
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void setPressed(boolean pressed) {
        if (!isSelected() && pressed || isSelected() && !pressed) {
            doClick();
        }
    }
}
