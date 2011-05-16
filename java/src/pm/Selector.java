package pm;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.selector.SelectButton;
import pm.selector.Selectable;

public class Selector<T extends Worker & Selectable> extends JFrame {
    protected Log log = LogFactory.getLog(getClass());
    protected static final long serialVersionUID = 1L;

    //protected ArrayList<T> activatableArray;

    public Selector(T[] activatableArray) {
        createPanel(activatableArray);
        setUndecorated(true);
        pack();
        setResizable(false);
        setVisible(true);
        log.debug("Selector constructed");
    }

    protected void createPanel(T[] activatableArray) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        for (T selectable : activatableArray) {
            String title = selectable.title();
            SelectButton<T> button = new SelectButton<T>(selectable);
            button.setText(title);
            panel.add(button);
            log.debug(String.format("Item added: %s", title));
        }
        add(panel);
    }
}
