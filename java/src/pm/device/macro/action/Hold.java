package pm.device.macro.action;

import pm.device.macro.Action;
import pm.device.macro.Button;

public class Hold implements Action {
    public Button button;
    
    public Hold(Button button) {
        this.button = button;
    }
}