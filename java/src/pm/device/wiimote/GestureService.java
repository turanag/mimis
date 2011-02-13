package pm.device.wiimote;

import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;

public class GestureService implements GestureListener {
    public void gestureReceived(GestureEvent event) {
        System.out.println(event);
    }
}
