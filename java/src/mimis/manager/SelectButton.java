package mimis.manager;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Action;
import javax.swing.JToggleButton;

import mimis.Worker;

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
            activatable.activate();
        } else {
            System.out.println("Deselected");
            activatable.deactivate();
        }
    }

    public void setPressed(boolean pressed) {
        getModel().setPressed(pressed);
    }
}
