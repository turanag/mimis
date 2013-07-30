package mimis.device.wiimote.motion;

import java.io.Serializable;

import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;

public class MotionData implements Serializable {
    protected static final long serialVersionUID = 1L;

    protected int time;
    protected MotionSensingEvent event;

    public MotionData(long time, MotionSensingEvent event) {
        this((int) time, event);
    }

    public MotionData(int time, MotionSensingEvent event) {
        this.time = time;
        this.event = event;
    }

    public int getTime() {
        return time;
    }

    public MotionSensingEvent getEvent() {
        return event;
    }
}
