package mimis.device.jintellitype;

import mimis.input.Task;
import mimis.state.TaskMap;
import mimis.state.TaskMapCycle;
import mimis.value.Action;
import mimis.value.Key;
import mimis.value.Target;

public class JIntellitypeTaskMapCycle extends TaskMapCycle {
    protected static final long serialVersionUID = 1L;
    
    public TaskMap mimis, player;
    
    public JIntellitypeTaskMapCycle() {
        /* Mimis */
        mimis = new TaskMap();
        mimis.add(
            new Hotkey(Key.PRIOR),
            new Task(Action.PREVIOUS, Target.MAIN));
        mimis.add(
            new Hotkey(Key.NEXT),
            new Task(Action.NEXT, Target.MAIN));
        add(mimis);
        
        /* Player */
        player = new TaskMap();
        player.add(
            CommandButton.VOLUME_DOWN,
            new Task(Action.VOLUME_DOWN, Target.APPLICATIONS));
        player.add(
            CommandButton.VOLUME_UP,
            new Task(Action.VOLUME_UP, Target.APPLICATIONS));
        player.add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 'x'),
            new Task(Action.EXIT, Target.MAIN));
        player.add(
            new Hotkey(Modifier.CTRL | Modifier.SHIFT | Modifier.WIN, 'n'),
            new Task(Action.NEXT, Target.CURRENT));
        player.add(
            new Hotkey(Modifier.CTRL | Modifier.SHIFT | Modifier.WIN, 'p'),
            new Task(Action.PREVIOUS, Target.CURRENT));
        add(player);
    }
}
