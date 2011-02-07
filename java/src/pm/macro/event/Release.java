package pm.macro.event;

import pm.Button;
import pm.macro.Event;

public class Release implements Event {
    public Button button;
    
    public Release(Button button) {
        this.button = button;
    }
}
