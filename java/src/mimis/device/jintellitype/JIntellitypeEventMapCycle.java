package mimis.device.jintellitype;

import mimis.device.EventMapCycle;
import mimis.event.Task;
import mimis.sequence.EventMap;
import mimis.sequence.state.Press;
import mimis.value.Action;
import mimis.value.Key;
import mimis.value.Target;

public class JIntellitypeEventMapCycle extends EventMapCycle {
    protected static final long serialVersionUID = 1L;
    
    public EventMap mimis, player;
    
    public JIntellitypeEventMapCycle() {
        /* Mimis */
        mimis = new EventMap();
        mimis.add(
            new Hotkey(Key.PRIOR),
            new Task(Target.MIMIS, Action.PREVIOUS));
        mimis.add(
            new Hotkey(Key.NEXT),
            new Task(Target.MIMIS, Action.NEXT));

        /* Player */
        player = new EventMap();
        player.add(
            new Press(CommandButton.VOLUME_DOWN),
            new Task(Target.APPLICATIONS, Action.VOLUME_DOWN));
        player.add(
            new Press(CommandButton.VOLUME_UP),
            new Task(Target.APPLICATIONS, Action.VOLUME_UP));
        player.add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 'x'),
            new Task(Target.MIMIS, Action.EXIT));
        player.add(
            new Hotkey(Modifier.CTRL | Modifier.SHIFT | Modifier.WIN, 'n'),
            new Task(Target.APPLICATION, Action.NEXT));
        player.add(
            new Hotkey(Modifier.CTRL | Modifier.SHIFT | Modifier.WIN, 'p'),
            new Task(Target.APPLICATION, Action.PREVIOUS));
    }
}
