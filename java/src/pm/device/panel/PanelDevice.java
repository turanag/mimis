package pm.device.panel;

import pm.Device;
import pm.event.Task;
import pm.macro.state.Press;
import pm.macro.state.Release;
import pm.value.Action;
import pm.value.Target;

public class PanelDevice extends Device implements PanelButtonListener {

    protected Panel panel;

    public void initialise() {
        panel = new Panel(this);
        panel.updateTime(12342398);
        panel.updatePosition(43);
        add(new Press(PanelButton.PLAY), new Task(Action.PLAY, Target.APPLICATION));
    }

    public void exit() {
        panel.dispose();
    }

    public void buttonPressed(PanelButton panelButton) {
        add(new Press(panelButton));
    }

    public void buttonReleased(PanelButton panelButton) {
        add(new Release(panelButton));
    }
}
