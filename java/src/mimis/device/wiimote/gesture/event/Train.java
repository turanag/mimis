package mimis.device.wiimote.gesture.event;

import org.wiigee.device.Device;
import org.wiigee.event.ButtonPressedEvent;

public class Train extends ButtonPressedEvent {
    protected static final long serialVersionUID = 1L;

    public Train(Device device) {
        super(device, 0);
    }

    public boolean isRecognitionInitEvent() {
        return false;
    }

    public boolean isTrainInitEvent() {
        return true;
    }

    public boolean isCloseGestureInitEvent() {
        return false;
    }
}
