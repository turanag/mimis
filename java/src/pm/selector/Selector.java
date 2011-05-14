package pm.selector;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pm.event.EventHandler;

public class Selector<T extends EventHandler> extends JFrame {

    protected static final long serialVersionUID = 1L;

    protected final static String TITLE = "MIMIS <T> Selector";

    private ArrayList<T> items;

    public Selector(ArrayList<T> items) {
        super(TITLE);
        this.items = items;
        System.out.println("Selector started");
        createPanel();
        pack();
        setResizable(false);
        setVisible(true);
    }

    protected void createPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        for (T t : items) {
            String name = t.title();
            SelectButton<T> button = new SelectButton<T>(t);
            button.setText(name);
            panel.add(button);
            System.out.println("Item added");
        }
        add(panel);
    }
}
