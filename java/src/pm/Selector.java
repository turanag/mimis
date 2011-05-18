package pm;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.selector.SelectButton;

public class Selector<T extends Worker> extends JPanel {
    protected Log log = LogFactory.getLog(getClass());
    protected static final long serialVersionUID = 1L;
    
    public Selector(String title) {
        setLayout(new GridLayout(0, 1));
        add(new JLabel(title, SwingConstants.CENTER));
        log.debug("Selector constructed");
    }
    
    protected void add(T worker, String title) {
        SelectButton<T> button = new SelectButton<T>(worker, title);
        add(button);
        log.debug(String.format("Item added: %s", title));       
    }
}
