package pm.device;

import java.util.Queue;

import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputAxisEventListener;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputButtonEventListener;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEventListener;

public class JavaInputListener implements JXInputAxisEventListener, JXInputButtonEventListener, JXInputDirectionalEventListener {

    protected Queue<JXInputAxisEvent> axisEventQueue;
    protected Queue<JXInputButtonEvent> buttonEventQueue;
    protected Queue<JXInputDirectionalEvent> directonalEventQueue;

    public JavaInputListener(Queue<JXInputAxisEvent> axisEventQueue, Queue<JXInputButtonEvent> buttonEventQueue, Queue<JXInputDirectionalEvent> directonalEventQueue) {
        this.axisEventQueue = axisEventQueue;
        this.buttonEventQueue = buttonEventQueue;
        this.directonalEventQueue = directonalEventQueue;
    }

    public void changed(JXInputAxisEvent event) {
        axisEventQueue.add(event);    
    }

    public void changed(JXInputButtonEvent event) {
        buttonEventQueue.add(event);
    }

    public void changed(JXInputDirectionalEvent event) {
        directonalEventQueue.add(event);        
    }
}
