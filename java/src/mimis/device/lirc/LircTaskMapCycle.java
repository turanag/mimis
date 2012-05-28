package mimis.device.lirc;

import mimis.device.lirc.remote.DenonRC176EventMap;
import mimis.device.lirc.remote.PhiliphsRCLE011EventMap;
import mimis.device.lirc.remote.SamsungBN5901015AEventMap;
import mimis.state.TaskMap;
import mimis.state.TaskMapCycle;

public class LircTaskMapCycle extends TaskMapCycle {
    protected static final long serialVersionUID = 1L;

    public TaskMap denonRC176, philiphsRCLE011, samsungBN5901015A;

    public LircTaskMapCycle() {      
        add(denonRC176 = new DenonRC176EventMap());
        add(philiphsRCLE011 = new PhiliphsRCLE011EventMap());
        add(samsungBN5901015A = new SamsungBN5901015AEventMap());
    }
}