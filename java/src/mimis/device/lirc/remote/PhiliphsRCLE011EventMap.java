package mimis.device.lirc.remote;

import mimis.event.Task;
import mimis.sequence.EventMap;
import mimis.value.Action;
import mimis.value.Target;

public class PhiliphsRCLE011EventMap extends EventMap {
    protected static final long serialVersionUID = 1L;
    
    public PhiliphsRCLE011EventMap() {
        /* Mimis */
        add(PhiliphsRCLE011Button.UP, new Task(Target.MIMIS, Action.NEXT));
        add(PhiliphsRCLE011Button.DOWN, new Task(Target.MIMIS, Action.PREVIOUS));

        /* Application */
        add(PhiliphsRCLE011Button.POWER, new Task(Target.APPLICATION, Action.ACTIVATE));
        add(PhiliphsRCLE011Button.PROGRAM_UP, new Task(Target.APPLICATION, Action.NEXT));
        add(PhiliphsRCLE011Button.PROGRAM_DOWN, new Task(Target.APPLICATION, Action.PREVIOUS));
        add(PhiliphsRCLE011Button.LEFT, new Task(Target.APPLICATION, Action.REWIND));
        add(PhiliphsRCLE011Button.TUNE, new Task(Target.APPLICATION, Action.PAUSE));
        add(PhiliphsRCLE011Button.RIGHT, new Task(Target.APPLICATION, Action.FORWARD));
        add(PhiliphsRCLE011Button.VOLUME_DOWN, new Task(Target.APPLICATION, Action.VOLUME_DOWN));
        add(PhiliphsRCLE011Button.MUTE, new Task(Target.APPLICATION, Action.MUTE));
        add(PhiliphsRCLE011Button.VOLUME_UP, new Task(Target.APPLICATION, Action.VOLUME_UP));
        add(PhiliphsRCLE011Button.VOLUME_UP, new Task(Target.APPLICATION, Action.VOLUME_UP));
        add(PhiliphsRCLE011Button.CLOCK, new Task(Target.APPLICATION, Action.REPEAT));
        add(PhiliphsRCLE011Button.OUT, new Task(Target.APPLICATION, Action.SHUFFLE));
    }
}
