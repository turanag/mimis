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
        //panel.updateTime(12342398);
        //panel.updatePosition(43);
        add(new Press(PanelButton.PREVIOUS), new Task(Action.PREVIOUS, Target.APPLICATION));
        add(new Press(PanelButton.REWIND), new Task(Action.REWIND, Target.APPLICATION));
        add(new Press(PanelButton.STOP), new Task(Action.STOP, Target.APPLICATION));
        add(new Press(PanelButton.PAUSE), new Task(Action.PAUSE, Target.APPLICATION));
        add(new Press(PanelButton.PLAY), new Task(Action.PLAY, Target.APPLICATION));
        add(new Press(PanelButton.FORWARD), new Task(Action.FORWARD, Target.APPLICATION));
        add(new Press(PanelButton.NEXT), new Task(Action.NEXT, Target.APPLICATION));
        add(new Press(PanelButton.VOLUME_DOWN), new Task(Action.VOLUME_DOWN, Target.APPLICATION));
        add(new Press(PanelButton.MUTE), new Task(Action.MUTE, Target.APPLICATION));
        add(new Press(PanelButton.VOLUME_UP), new Task(Action.VOLUME_UP, Target.APPLICATION));
    }

    public void exit() {
        panel.dispose();
    }

    public void buttonPressed(PanelButton panelButton) {
        //Vang hier toggles af om bijvoorbeeld de play/pause en mute knop en veranderen
        add(new Press(panelButton));
    }

    public void buttonReleased(PanelButton panelButton) {
        add(new Release(panelButton));
    }
}
