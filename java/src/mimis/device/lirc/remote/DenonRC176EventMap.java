package mimis.device.lirc.remote;

import mimis.event.Task;
import mimis.sequence.EventMap;
import mimis.value.Action;
import mimis.value.Target;

public class DenonRC176EventMap extends EventMap {
    protected static final long serialVersionUID = 1L;
    
    public DenonRC176EventMap() {
        /* Mimis */
        add(DenonRC176Button.TUNER_UP, new Task(Target.MIMIS, Action.NEXT));
        add(DenonRC176Button.TUNER_DOWN, new Task(Target.MIMIS, Action.PREVIOUS));

        /* Application */
        add(DenonRC176Button.AMP_POWER, new Task(Target.APPLICATION, Action.ACTIVATE));
        add(DenonRC176Button.CD_NEXT, new Task(Target.APPLICATION, Action.NEXT));
        add(DenonRC176Button.CD_PREVIOUS, new Task(Target.APPLICATION, Action.PREVIOUS));
        add(DenonRC176Button.TAPE_REWIND, new Task(Target.APPLICATION, Action.REWIND));
        add(DenonRC176Button.CD_PLAY, new Task(Target.APPLICATION, Action.PLAY));
        add(DenonRC176Button.CD_PAUSE, new Task(Target.APPLICATION, Action.PLAY));
        add(DenonRC176Button.TAPE_FORWARD, new Task(Target.APPLICATION, Action.FORWARD));
        add(DenonRC176Button.AMP_MUTE, new Task(Target.APPLICATION, Action.MUTE));
        add(DenonRC176Button.AMP_VOLUME_UP, new Task(Target.APPLICATION, Action.VOLUME_UP));
        add(DenonRC176Button.AMP_VOLUME_DOWN, new Task(Target.APPLICATION, Action.VOLUME_DOWN));
        add(DenonRC176Button.CD_REPEAT, new Task(Target.APPLICATION, Action.REPEAT));
        add(DenonRC176Button.CD_SHUFFLE, new Task(Target.APPLICATION, Action.SHUFFLE));
        add(DenonRC176Button.TAPE_AB, new Task(Target.APPLICATION, Action.LIKE));
        add(DenonRC176Button.TAPE_REC, new Task(Target.APPLICATION, Action.DISLIKE));
    }
}
