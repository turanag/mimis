package pm.device.macro.action;

import pm.device.macro.Action;
import pm.device.macro.Button;

public class Press implements Action {
    public Button button;
    
    public Press(Button button) {
        this.button = button;
    }
}
