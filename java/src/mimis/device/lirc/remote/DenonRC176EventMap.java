package mimis.device.lirc.remote;

import mimis.event.Task;
import mimis.sequence.EventMap;
import mimis.value.Action;
import mimis.value.Target;

public class DenonRC176EventMap extends EventMap {
    protected static final long serialVersionUID = 1L;
    
    public DenonRC176EventMap() {
        add(DenonRC176Button.AMP_VOLUME_UP, new Task(Target.APPLICATION, Action.VOLUME_UP));
    }
}
