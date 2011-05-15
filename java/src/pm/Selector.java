package pm;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.selector.Activatable;
import pm.selector.SelectButton;

public class Selector<T extends Activatable> extends JFrame {
    protected Log log = LogFactory.getLog(getClass());
    protected static final long serialVersionUID = 1L;

    protected ArrayList<Activatable> activatableArray;

    public Selector(Activatable[] activatableArray) {
        createPanel(activatableArray);
        //setUndecorated(true);
        pack();
        setResizable(false);
        setVisible(true);
        log.debug("Selector constructed");
    }

    protected void createPanel(Activatable[] activatableArray) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        for (Activatable activatable : activatableArray) {
            String title = activatable.title();
            SelectButton<Activatable> button = new SelectButton<Activatable>(activatable);
            button.setText(title);
            panel.add(button);
            log.debug(String.format("Item added: %s", title));
        }
        add(panel);
    }
}
