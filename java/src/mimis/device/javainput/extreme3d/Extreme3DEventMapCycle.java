package mimis.device.javainput.extreme3d;

import mimis.device.EventMapCycle;
import mimis.device.javainput.DirectionButton;
import mimis.event.Task;
import mimis.sequence.EventMap;
import mimis.sequence.state.Press;
import mimis.value.Action;
import mimis.value.Target;

public class Extreme3DEventMapCycle extends EventMapCycle {
    protected static final long serialVersionUID = 1L;

    public EventMap mimis, player, like;

    public Extreme3DEventMapCycle() {
        /* Mimis */
        mimis = new EventMap();
        mimis.add(
            new Press(Extreme3DButton.SEVEN),
            new Task(Target.MIMIS, Action.PREVIOUS));
        mimis.add(
            new Press(Extreme3DButton.EIGHT),
            new Task(Target.MIMIS, Action.NEXT));
        add(mimis);
        
        /* Player */
        player = new EventMap();
        player.add(
            new Press(Extreme3DButton.ONE),
            new Task(Target.APPLICATION, Action.PLAY));
        player.add(
            new Press(Extreme3DButton.TWO),
            new Task(Target.APPLICATION, Action.MUTE));
        player.add(
            new Press(Extreme3DButton.NINE),
            new Task(Target.APPLICATION, Action.SHUFFLE));
        player.add(
            new Press(Extreme3DButton.TEN),
            new Task(Target.APPLICATION, Action.REPEAT));
        player.add(
            new Press(Extreme3DButton.SIX),
            new Task(Target.APPLICATION, Action.NEXT));
        player.add(
            new Press(Extreme3DButton.FOUR),
            new Task(Target.APPLICATION, Action.PREVIOUS));        
        player.add(
            new Press(Extreme3DButton.FIVE),
            new Task(Target.APPLICATION, Action.FORWARD));
        player.add(
            new Press(Extreme3DButton.THREE),
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
            new Press(Extreme3DButton.ELEVEN),
            new Task(Target.APPLICATION, Action.LIKE));
        like.add(
            new Press(Extreme3DButton.TWELVE),
            new Task(Target.APPLICATION, Action.DISLIKE));
        add(like);
    }
}
