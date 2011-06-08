package mimis.device.javainput.rumblepad;

import mimis.device.EventMapCycle;
import mimis.device.javainput.DirectionButton;
import mimis.event.Task;
import mimis.sequence.EventMap;
import mimis.sequence.state.Press;
import mimis.value.Action;
import mimis.value.Target;

public class RumblepadEventMapCycle extends EventMapCycle {
    protected static final long serialVersionUID = 1L;

    public EventMap mimis, player, like;

    public RumblepadEventMapCycle() {
        /* Mimis */
        mimis = new EventMap();
        mimis.add(
            new Press(RumblepadButton.ONE),
            new Task(Target.MIMIS, Action.PREVIOUS));
        mimis.add(
            new Press(RumblepadButton.THREE),
            new Task(Target.MIMIS, Action.NEXT));
        add(mimis);
        
        /* Player */
        player = new EventMap();
        player.add(
            new Press(DirectionButton.WEST),
            new Task(Target.APPLICATION, Action.PLAY));
        player.add(
            new Press(DirectionButton.EAST),
            new Task(Target.APPLICATION, Action.MUTE));
        player.add(
            new Press(RumblepadButton.NINE),
            new Task(Target.APPLICATION, Action.SHUFFLE));
        player.add(
            new Press(RumblepadButton.TEN),
            new Task(Target.APPLICATION, Action.REPEAT));
        player.add(
            new Press(RumblepadButton.EIGHT),
            new Task(Target.APPLICATION, Action.NEXT));
        player.add(
            new Press(RumblepadButton.SIX),
            new Task(Target.APPLICATION, Action.PREVIOUS));        
        player.add(
            new Press(RumblepadButton.SEVEN),
            new Task(Target.APPLICATION, Action.FORWARD));
        player.add(
            new Press(RumblepadButton.FIVE),
            new Task(Target.APPLICATION, Action.REWIND));
        player.add(
            new Press(DirectionButton.SOUTH),
            new Task(Target.APPLICATION, Action.VOLUME_DOWN));
        player.add(
            new Press(DirectionButton.NORTH),
            new Task(Target.APPLICATION, Action.VOLUME_UP));        
        add(player);

        like = new EventMap();
        like.add(
            new Press(RumblepadButton.FOUR),
            new Task(Target.APPLICATION, Action.LIKE));
        like.add(
            new Press(RumblepadButton.TWO),
            new Task(Target.APPLICATION, Action.DISLIKE));
        add(like);
    }
}
