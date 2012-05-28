package mimis.device.lirc.remote;

import mimis.input.Task;
import mimis.state.TaskMap;
import mimis.value.Action;
import mimis.value.Target;

public class PhiliphsRCLE011EventMap extends TaskMap {
    protected static final long serialVersionUID = 1L;
    
    public PhiliphsRCLE011EventMap() {
        /* Mimis */
        add(PhiliphsRCLE011Button.UP, new Task(Action.NEXT, Target.MAIN));
        add(PhiliphsRCLE011Button.DOWN, new Task(Action.PREVIOUS, Target.MAIN));

        /* Application */
        add(PhiliphsRCLE011Button.POWER, new Task(Action.START, Target.CURRENT));
        add(PhiliphsRCLE011Button.PROGRAM_UP, new Task(Action.NEXT, Target.CURRENT));
        add(PhiliphsRCLE011Button.PROGRAM_DOWN, new Task(Action.PREVIOUS, Target.CURRENT));
        add(PhiliphsRCLE011Button.LEFT, new Task(Action.REWIND, Target.CURRENT));
        add(PhiliphsRCLE011Button.TUNE, new Task(Action.PLAY, Target.CURRENT));
        add(PhiliphsRCLE011Button.RIGHT, new Task(Action.FORWARD, Target.CURRENT));
        add(PhiliphsRCLE011Button.VOLUME_DOWN, new Task(Action.VOLUME_DOWN, Target.CURRENT));
        add(PhiliphsRCLE011Button.MUTE, new Task(Action.MUTE, Target.CURRENT));
        add(PhiliphsRCLE011Button.VOLUME_UP, new Task(Action.VOLUME_UP, Target.CURRENT));
        add(PhiliphsRCLE011Button.CLOCK, new Task(Action.REPEAT, Target.CURRENT));
        add(PhiliphsRCLE011Button.OUT, new Task(Action.SHUFFLE, Target.CURRENT));
        add(PhiliphsRCLE011Button.SQUARE, new Task(Action.FULLSCREEN, Target.CURRENT));
        add(PhiliphsRCLE011Button.RED, new Task(Action.DISLIKE, Target.CURRENT));
        add(PhiliphsRCLE011Button.GREEN, new Task(Action.LIKE, Target.CURRENT));
    }
}
