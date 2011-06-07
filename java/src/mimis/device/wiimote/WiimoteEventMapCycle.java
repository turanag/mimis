package mimis.device.wiimote;

import mimis.Macro;
import mimis.device.EventMapCycle;
import mimis.event.Task;
import mimis.exception.macro.StateOrderException;
import mimis.sequence.EventMap;
import mimis.sequence.state.Hold;
import mimis.sequence.state.Press;
import mimis.sequence.state.Release;
import mimis.value.Action;
import mimis.value.Target;

public class WiimoteEventMapCycle extends EventMapCycle {
    protected static final long serialVersionUID = 1L;

    public EventMap mimis, player, gesture, like;

    public WiimoteEventMapCycle() {
        /* Mimis */
        mimis = new EventMap();
        mimis.add(
            new Press(WiimoteButton.HOME),
            new Task(Target.MIMIS, Action.NEXT));
        
        /* Gesture */
        gesture = new EventMap();
        gesture.add(
            new Press(WiimoteButton.A),
            new Task(Action.TRAIN));
        gesture.add(
            new Press(WiimoteButton.B),
            new Task(Action.SAVE));
        gesture.add(
            new Press(WiimoteButton.DOWN),
            new Task(Action.LOAD));
        gesture.add(
            new Press(WiimoteButton.HOME),
            new Task(Action.RECOGNIZE));
        add(gesture);

        /* Player */
        player = new EventMap();
        player.add(
            new Press(WiimoteButton.A),
            new Task(Target.APPLICATION, Action.PLAY));
        player.add(
            new Press(WiimoteButton.B),
            new Task(Target.APPLICATION, Action.MUTE));
        player.add(
            new Press(WiimoteButton.ONE),
            new Task(Target.APPLICATION, Action.SHUFFLE));
        player.add(
            new Press(WiimoteButton.TWO),
            new Task(Target.APPLICATION, Action.REPEAT));
        player.add(
            new Press(WiimoteButton.UP),
            new Task(Target.APPLICATION, Action.NEXT));
        player.add(
            new Press(WiimoteButton.DOWN),
            new Task(Target.APPLICATION, Action.PREVIOUS));        
        player.add(
            new Press(WiimoteButton.RIGHT),
            new Task(Target.APPLICATION, Action.FORWARD));
        player.add(
            new Press(WiimoteButton.LEFT),
            new Task(Target.APPLICATION, Action.REWIND));
        player.add(
            new Press(WiimoteButton.MINUS),
            new Task(Target.APPLICATION, Action.VOLUME_DOWN));
        player.add(
            new Press(WiimoteButton.PLUS),
            new Task(Target.APPLICATION, Action.VOLUME_UP));        
        add(player);

        /* Like */
        try {
            like = new EventMap();
            like.add(
                new Macro(
                    new Hold(WiimoteButton.TWO),
                    new Press(WiimoteButton.PLUS),
                    new Release(WiimoteButton.TWO)),
                new Task(Target.APPLICATION, Action.LIKE));
            like.add(
                new Macro(
                    new Hold(WiimoteButton.TWO),
                    new Press(WiimoteButton.MINUS),
                    new Release(WiimoteButton.TWO)),
                new Task(Target.APPLICATION, Action.DISLIKE));
            add(like);
        } catch (StateOrderException e) {
            log.error(e);
        }
    }
}
