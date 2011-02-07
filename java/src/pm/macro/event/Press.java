package pm.macro.event;

import pm.Button;
import pm.macro.Event;

public class Press implements Event {
    public Button button;
    
    public Press(Button button) {
        this.button = button;
    }
}
