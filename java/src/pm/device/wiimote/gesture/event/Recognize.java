package pm.device.wiimote.gesture.event;

import org.wiigee.device.Device;
import org.wiigee.event.ButtonPressedEvent;

public class Recognize extends ButtonPressedEvent {
    protected static final long serialVersionUID = 1L;

    public Recognize(Device device) {
        super(device, 0);
    }

    public boolean isRecognitionInitEvent() {
        return true;
    }

    public boolean isTrainInitEvent() {
        return false;
    }

    public boolean isCloseGestureInitEvent() {
        return false;
    }
}
