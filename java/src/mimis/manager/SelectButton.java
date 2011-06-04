package mimis.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import mimis.Worker;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SelectButton<T extends Worker & Titled> extends JButton implements ActionListener {
    protected Log log = LogFactory.getLog(getClass());

    protected static final long serialVersionUID = 1L;
    protected T activatable;
    protected Action action;

    public SelectButton(T activatable) {
        this.activatable = activatable;
        setText(activatable.title());
        setFocusable(false);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (activatable.active()) {
            try {
                activatable.deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        } else {
            try {
                activatable.activate();
            } catch (ActivateException e) {
                log.error(e);
            }
        }        
    }
}
