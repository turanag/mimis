package mimis.device.wiimote.gesture.event;

import org.wiigee.device.Device;
import org.wiigee.event.ButtonPressedEvent;

public class Close extends ButtonPressedEvent {
    protected static final long serialVersionUID = 1L;

    public Close(Device device) {
        super(device, 0);
    }

    public boolean isRecognitionInitEvent() {
        return false;
    }

    public boolean isTrainInitEvent() {
        return false;
    }

    public boolean isCloseGestureInitEvent() {
        return true;
    }
}
