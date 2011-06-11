package mimis.device.wiimote;

import mimis.device.EventMapCycle;
import mimis.event.Task;
import mimis.sequence.EventMap;
import mimis.sequence.state.Release;
import mimis.value.Action;
import mimis.value.Target;

public class WiimoteEventMapCycle extends EventMapCycle {
    protected static final long serialVersionUID = 1L;

    public EventMap mimis, player, gesture, like, shift;

    public WiimoteEventMapCycle() {
        /* Mimis */
        mimis = new EventMap();
        mimis.add(WiimoteButton.HOME, new Task(Target.MIMIS, Action.NEXT));
        mimis.add(new Release(WiimoteButton.B), new Task(Target.SELF, Action.UNSHIFT));

        /* Gesture */
        gesture = new EventMap();
        gesture.add(WiimoteButton.A, new Task(Action.TRAIN));
        gesture.add(WiimoteButton.B, new Task(Action.SAVE));
        gesture.add(WiimoteButton.DOWN, new Task(Action.LOAD));
        gesture.add(WiimoteButton.HOME, new Task(Action.RECOGNIZE));
        add(gesture);

        /* Player */
        player = new EventMap();
        player.add(WiimoteButton.A, new Task(Target.APPLICATION, Action.PLAY));
        player.add(WiimoteButton.B, new Task(Target.SELF, Action.SHIFT));
        player.add(WiimoteButton.HOME, new Task(Target.APPLICATION, Action.MUTE));
        player.add(WiimoteButton.ONE, new Task(Target.APPLICATION, Action.SHUFFLE));
        player.add(WiimoteButton.TWO, new Task(Target.APPLICATION, Action.REPEAT));
        player.add(WiimoteButton.UP, new Task(Target.APPLICATION, Action.NEXT));
        player.add(WiimoteButton.DOWN, new Task(Target.APPLICATION, Action.PREVIOUS));        
        player.add(WiimoteButton.RIGHT, new Task(Target.APPLICATION, Action.FORWARD));
        player.add(WiimoteButton.LEFT, new Task(Target.APPLICATION, Action.REWIND));
        player.add(WiimoteButton.MINUS, new Task(Target.APPLICATION, Action.VOLUME_DOWN));
        player.add(WiimoteButton.PLUS, new Task(Target.APPLICATION, Action.VOLUME_UP));        
        add(player);

        /* Like */
        like = new EventMap();
        like.add(WiimoteButton.PLUS, new Task(Target.APPLICATION, Action.LIKE));
        like.add(WiimoteButton.MINUS, new Task(Target.APPLICATION, Action.DISLIKE));
        add(like);
    }
}
