package mimis.device.lirc.remote;

import mimis.input.Task;
import mimis.state.TaskMap;
import mimis.value.Action;
import mimis.value.Target;

public class SamsungBN5901015AEventMap extends TaskMap {
    protected static final long serialVersionUID = 1L;

    public SamsungBN5901015AEventMap() {
        add(SamsungBN5901015AButton.VOLUME_UP, new Task(Action.VOLUME_UP, Target.CURRENT));
    }
}
