package pm.selector;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JToggleButton;

import pm.event.EventHandler;
import pm.exception.InitialiseException;

public class SelectButton<T extends EventHandler> extends JToggleButton implements ItemListener {

    protected static final long serialVersionUID = 1L;
    
    protected EventHandler eventHandler;

    public SelectButton(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        addItemListener(this);
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        int state = itemEvent.getStateChange();
        if (state == ItemEvent.SELECTED) {
            System.out.println("Selected");
            try {
                eventHandler.initialise();
                eventHandler.start();
            } catch (InitialiseException e) {
                e.printStackTrace();
            }
        } else {
             System.out.println("Deselected");
             eventHandler.stop();
        }
        //System.out.println(itemEvent.getSource());        
    }
}
