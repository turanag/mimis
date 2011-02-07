package pm.macro.event;

import pm.Button;
import pm.macro.Event;

public class Hold implements Event {
    public Button button;
    
    public Hold(Button button) {
        this.button = button;
    }
}