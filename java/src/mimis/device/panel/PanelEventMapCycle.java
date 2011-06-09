package mimis.device.panel;

import mimis.device.EventMapCycle;
import mimis.event.Task;
import mimis.sequence.EventMap;
import mimis.sequence.state.Press;
import mimis.value.Action;
import mimis.value.Target;

public class PanelEventMapCycle extends EventMapCycle {
    protected static final long serialVersionUID = 1L;

    public EventMap player;
    public PanelEventMapCycle() {
        /* Player */
        player = new EventMap();
        player.add(new Press(PanelButton.UP), new Task(Target.MIMIS, Action.PREVIOUS));
        player.add(new Press(PanelButton.PREVIOUS), new Task(Target.APPLICATION, Action.PREVIOUS));
        player.add(new Press(PanelButton.REWIND), new Task(Target.APPLICATION, Action.REWIND));
        player.add(new Press(PanelButton.STOP), new Task(Target.APPLICATION, Action.STOP));
        player.add(new Press(PanelButton.PAUSE), new Task(Target.APPLICATION, Action.PAUSE));
        player.add(new Press(PanelButton.PLAY), new Task(Target.APPLICATION, Action.PLAY));
        player.add(new Press(PanelButton.FORWARD), new Task(Target.APPLICATION, Action.FORWARD));
        player.add(new Press(PanelButton.DOWN), new Task(Target.MIMIS, Action.NEXT));
        player.add(new Press(PanelButton.NEXT), new Task(Target.APPLICATION, Action.NEXT));
        player.add(new Press(PanelButton.VOLUME_DOWN), new Task(Target.APPLICATION, Action.VOLUME_DOWN));
        player.add(new Press(PanelButton.MUTE), new Task(Target.APPLICATION, Action.MUTE));
        player.add(new Press(PanelButton.VOLUME_UP), new Task(Target.APPLICATION, Action.VOLUME_UP));
        player.add(new Press(PanelButton.REPEAT), new Task(Target.APPLICATION, Action.REPEAT));
        player.add(new Press(PanelButton.SHUFFLE), new Task(Target.APPLICATION, Action.SHUFFLE));
        add(player);
    }
}
