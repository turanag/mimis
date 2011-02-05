package pm.device;

import java.util.LinkedList;
import java.util.Queue;

import de.hardcode.jxinput.Axis;
import de.hardcode.jxinput.Button;
import de.hardcode.jxinput.Directional;
import de.hardcode.jxinput.JXInputDevice;
import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;
import de.hardcode.jxinput.event.JXInputEventManager;

import pm.exception.ServiceJavaInputDeviceNotFoundException;
import pm.exception.ServiceJavaInputException;

public abstract class JavaInputDevice extends Device {
    protected static int SLEEP = 100;

    public boolean run = true;

    public JavaInputListener javaInputListener;
    public Queue<JXInputAxisEvent> axisEventQueue;
    public Queue<JXInputButtonEvent> buttonEventQueue;
    public Queue<JXInputDirectionalEvent> directionalEventQueue;
    //public static JavaInputService jxinputService;

    protected JXInputDevice jxinputDevice;

    protected JavaInputDevice(String name) throws ServiceJavaInputException {
        axisEventQueue = new LinkedList<JXInputAxisEvent>();
        buttonEventQueue = new LinkedList<JXInputButtonEvent>();
        directionalEventQueue = new LinkedList<JXInputDirectionalEvent>();
        javaInputListener = new JavaInputListener(axisEventQueue, buttonEventQueue, directionalEventQueue);
        
        /*if (jxinputService == null) {
            throw new ServiceJavaInputException();
        }*/
        //JXInputDevice x = jxinputService.getDevice(name);
        jxinputDevice = getDevice(name);
        System.out.printf("Initialized: %s\n", jxinputDevice.getName());
   }

    public static JXInputDevice getDevice(String name) throws ServiceJavaInputException {
        int numberOfDevices = JXInputManager.getNumberOfDevices();
        for (int i = 0; i < numberOfDevices; ++i) {
            JXInputDevice device = JXInputManager.getJXInputDevice(i);
            if (device.getName().startsWith(name)) {
                return device;
            }
        }
        throw new ServiceJavaInputDeviceNotFoundException();
    }

    public void start() {
        startListeners();
        while (run) {
            boolean sleep = true;
            if (!axisEventQueue.isEmpty()) {
                processEvent(axisEventQueue.poll());
                sleep = false;
            }
            if (!buttonEventQueue.isEmpty()) {
                processEvent(buttonEventQueue.poll());
                sleep = false;
            }
            if (!directionalEventQueue.isEmpty()) {
                processEvent(directionalEventQueue.poll());
                sleep = false;
            }
            if (sleep) {
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {}
            }
        }
    }

    protected void processEvent(JXInputAxisEvent event) {
        
    }

    protected void processEvent(JXInputButtonEvent event) {
        System.out.println(event);
    }

    protected void processEvent(JXInputDirectionalEvent event) {
        
    }

    protected void startListeners() {
        for (int i = 0; i < jxinputDevice.getMaxNumberOfAxes(); ++i) {
            Axis axis = jxinputDevice.getAxis(i);
            if (axis != null) {
                JXInputEventManager.addListener(javaInputListener, axis);
            }
        }
        for (int i = 0; i < jxinputDevice.getMaxNumberOfButtons(); ++i) {
            Button button = jxinputDevice.getButton(i);
            if (button != null) {
                JXInputEventManager.addListener(javaInputListener, button);
            }
        }
        for (int i = 0; i < jxinputDevice.getMaxNumberOfDirectionals(); ++i) {
            Directional directional = jxinputDevice.getDirectional(i);
            if (directional != null) {
                JXInputEventManager.addListener(javaInputListener, directional);
            }
        }        
    }

    public void exit() {
        run = false;
    }
}
