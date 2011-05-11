package pm;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

public class ApplicationButton extends JToggleButton implements ItemListener {
    protected Application application;

    public ApplicationButton(Application application) {
        this.application = application;
        addItemListener(this);
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        int state = itemEvent.getStateChange();
        if (state == ItemEvent.SELECTED) {
            System.out.println("Selected");
        } else {
             System.out.println("Deselected");
        }
        //System.out.println(itemEvent.getSource());        
    }
}
