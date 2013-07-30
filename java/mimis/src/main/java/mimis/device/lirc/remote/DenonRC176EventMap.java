package mimis.device.lirc.remote;

import mimis.input.Task;
import mimis.state.TaskMap;
import mimis.value.Action;
import mimis.value.Target;

public class DenonRC176EventMap extends TaskMap {
    protected static final long serialVersionUID = 1L;
    
    public DenonRC176EventMap() {
        /* Mimis */
        add(DenonRC176Button.TUNER_UP, new Task(Action.NEXT, Target.MAIN));
        add(DenonRC176Button.TUNER_DOWN, new Task(Action.PREVIOUS, Target.MAIN));

        /* Application */
        add(DenonRC176Button.AMP_POWER, new Task(Action.START, Target.CURRENT));
        add(DenonRC176Button.CD_NEXT, new Task(Action.NEXT, Target.CURRENT));
        add(DenonRC176Button.CD_PREVIOUS, new Task(Action.PREVIOUS, Target.CURRENT));
        add(DenonRC176Button.TAPE_REWIND, new Task(Action.REWIND, Target.CURRENT));
        add(DenonRC176Button.CD_PLAY, new Task(Action.PLAY, Target.CURRENT));
        add(DenonRC176Button.CD_PAUSE, new Task(Action.PLAY, Target.CURRENT));
        add(DenonRC176Button.TAPE_FORWARD, new Task(Action.FORWARD, Target.CURRENT));
        add(DenonRC176Button.AMP_MUTE, new Task(Action.MUTE, Target.CURRENT));
        add(DenonRC176Button.AMP_VOLUME_UP, new Task(Action.VOLUME_UP, Target.CURRENT));
        add(DenonRC176Button.AMP_VOLUME_DOWN, new Task(Action.VOLUME_DOWN, Target.CURRENT));
        add(DenonRC176Button.CD_REPEAT, new Task(Action.REPEAT, Target.CURRENT));
        add(DenonRC176Button.CD_SHUFFLE, new Task(Action.SHUFFLE, Target.CURRENT));
        add(DenonRC176Button.TAPE_AB, new Task(Action.LIKE, Target.CURRENT));
        add(DenonRC176Button.TAPE_REC, new Task(Action.DISLIKE, Target.CURRENT));
    }
}
