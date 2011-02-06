package pm.device.javainput;

import java.util.LinkedList;
import java.util.Queue;

import pm.exception.EventException;

import de.hardcode.jxinput.Axis;
import de.hardcode.jxinput.Button;
import de.hardcode.jxinput.Directional;
import de.hardcode.jxinput.JXInputDevice;
import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputAxisEventListener;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputButtonEventListener;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEventListener;
import de.hardcode.jxinput.event.JXInputEventManager;

public class JavaInputListener extends Thread implements JXInputAxisEventListener, JXInputButtonEventListener, JXInputDirectionalEventListener {
    protected static int SLEEP = 100;

    protected boolean run;
    protected JavaInputDevice javaInputDevice;
    protected JXInputDevice jxinputDevice;
    protected Queue<JXInputAxisEvent> axisEventQueue;
    protected Queue<JXInputButtonEvent> buttonEventQueue;
    protected Queue<JXInputDirectionalEvent> directionalEventQueue;

    public JavaInputListener(JavaInputDevice javaInputDevice, JXInputDevice jxinputDevice) {
        this.javaInputDevice = javaInputDevice;
        this.jxinputDevice = jxinputDevice;
        axisEventQueue = new LinkedList<JXInputAxisEvent>();
        buttonEventQueue = new LinkedList<JXInputButtonEvent>();
        directionalEventQueue = new LinkedList<JXInputDirectionalEvent>();
        addListeners();
    }

    protected void addListeners() {
        for (int i = 0; i < jxinputDevice.getMaxNumberOfAxes(); ++i) {
            Axis axis = jxinputDevice.getAxis(i);
            if (axis != null) {
                JXInputEventManager.addListener(this, axis);
            }
        }
        for (int i = 0; i < jxinputDevice.getMaxNumberOfButtons(); ++i) {
            Button button = jxinputDevice.getButton(i);
            if (button != null) {
                JXInputEventManager.addListener(this, button);
            }
        }
        for (int i = 0; i < jxinputDevice.getMaxNumberOfDirectionals(); ++i) {
            Directional directional = jxinputDevice.getDirectional(i);
            if (directional != null) {
                JXInputEventManager.addListener(this, directional);
            }
        }
    }

    public void changed(JXInputAxisEvent event) {
        axisEventQueue.add(event);    
    }

    public void changed(JXInputButtonEvent event) {
        buttonEventQueue.add(event);
    }

    public void changed(JXInputDirectionalEvent event) {
        directionalEventQueue.add(event);        
    }

    public void run() {
        run = true;
        while (run) {
            JXInputManager.updateFeatures();
            boolean sleep = true;
            if (!axisEventQueue.isEmpty()) {
                javaInputDevice.processEvent(axisEventQueue.poll());
                sleep = false;
            }
            if (!buttonEventQueue.isEmpty()) {
                try {
                    javaInputDevice.processEvent(buttonEventQueue.poll());
                } catch (EventException e) {}
                sleep = false;
            }
            if (!directionalEventQueue.isEmpty()) {
                try {
                    javaInputDevice.processEvent(directionalEventQueue.poll());
                } catch (EventException e) {e.printStackTrace();}
                sleep = false;
            }
            if (sleep) {
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {}
            }
        }
    }
}
