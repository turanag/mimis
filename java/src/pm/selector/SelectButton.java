package pm.selector;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JToggleButton;

public class SelectButton<T extends Activatable> extends JToggleButton implements ItemListener {
    protected static final long serialVersionUID = 1L;
    
    protected T activatable;

    public SelectButton(T activatable) {
        this.activatable = activatable;
        addItemListener(this);
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        int state = itemEvent.getStateChange();
        if (state == ItemEvent.SELECTED) {
            System.out.println("Selected");
            activatable.activate();
        } else {
             System.out.println("Deselected");
             activatable.deactivate();
        }
        //System.out.println(itemEvent.getSource());        
    }
}
