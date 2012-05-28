package mimis.manager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JToggleButton;

import mimis.worker.Component;
import mimis.worker.Worker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WorkerButton extends JToggleButton implements MouseListener {
    protected static final long serialVersionUID = 1L;
    protected Log log = LogFactory.getLog(getClass());

    protected Worker worker;

    public WorkerButton(Worker worker) {
        this.worker = worker;
        setFocusable(false);
        addMouseListener(this);
        if (worker instanceof Component) {
            setText(((Component) worker).getTitle());
        }
    }

    public void mouseClicked(MouseEvent event) {
        if (worker.active()) {
            log.trace("Stop");
            worker.stop();
        } else {
            log.trace("Start");
            worker.start();
        }        
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void setPressed(boolean pressed) {
        if ((!isSelected() && pressed) || (isSelected() && !pressed)) {
            doClick();
        }
    }
}
