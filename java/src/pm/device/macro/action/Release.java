package pm.device.macro.action;

import pm.device.macro.Action;
import pm.device.macro.Button;

public class Release implements Action {
    public Button button;
    
    public Release(Button button) {
        this.button = button;
    }
}
