package mimis.device.wiimote;

import mimis.Button;
import mimis.Device;
import mimis.Worker;
import mimis.device.wiimote.gesture.GestureDevice;
import mimis.event.Feedback;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.device.DeviceNotFoundException;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.sequence.state.Press;
import mimis.sequence.state.Release;
import mimis.util.ArrayCycle;
import mimis.value.Action;

import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;
import org.wiigee.util.Log;

import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

public class WiimoteDevice extends Device implements GestureListener {
    protected static final String TITLE = "Wiimote";
    protected static final int RUMBLE = 50;
    protected static final int TIMEOUT = 200;

    protected static WiimoteService wiimoteService;
    protected WiimoteEventMapCycle eventMapCycle;
    protected WiimoteDiscovery wiimoteDiscovery;
    protected Wiimote wiimote;
    protected boolean connected;
    protected GestureDevice gestureDevice;
    protected int gestureId;
    protected LedWorker ledWorker;

    static {
        WiimoteDevice.wiimoteService = new WiimoteService();
        Log.setLevel(0);
    }

    public WiimoteDevice() {
        super(TITLE);
        eventMapCycle = new WiimoteEventMapCycle();
        wiimoteDiscovery = new WiimoteDiscovery(this);
        gestureDevice = new GestureDevice();
        gestureDevice.add(this);
        gestureId = 0;
        ledWorker = new LedWorker();
    }

    /* Worker */
    public void activate() throws ActivateException {
        connect();
        add(eventMapCycle.player);
        super.activate();
    }

    public boolean active() {
        if (wiimote != null) {
            if (!ledWorker.active()) {
                try {
                    ledWorker.activate();
                } catch (ActivateException e) {
                    log.error(e);
                }
            }
            connected = false;
            wiimote.getStatus();
            synchronized (this) {
                try {
                    wait(TIMEOUT);
                } catch (InterruptedException e) {
                    log.error(e);
                }
            }
            if (!connected) {                
                active = false;
                try {
                    ledWorker.deactivate();
                } catch (DeactivateException e) {
                    log.error(e);
                }
            }
        }
        return active;
    }

    public void stop() {
        super.stop();
        ledWorker.stop();
        if (wiimote != null) {
            disconnect();
        }
        wiimoteService.exit();
        wiimoteDiscovery.stop();
    }

    /* Events */
    public void begin(Action action) {
        switch (action) {
            case SHIFT:
                log.debug("Shift");
                reset();
                add(eventMapCycle.mimis);
                add(eventMapCycle.like);
                break;
            case UNSHIFT:
                log.debug("Unshift");
                reset();
                add(eventMapCycle.player);
                break;
            case TRAIN:
                log.debug("Gesture train");
                gestureDevice.train();
                break;
            case SAVE:
                log.debug("Gesture save");
                gestureDevice.close();
                gestureDevice.saveGesture(gestureId, "C:\\gesture-" + gestureId);
                ++gestureId;
                break;
            case LOAD:
                log.debug("Gesture load");
                for (int i = 0; i < gestureId; ++i) {
                    gestureDevice.loadGesture("C:\\gesture-" + i);
                }
                break;
            case RECOGNIZE:
                log.debug("Gesture recognize");
                gestureDevice.recognize();
                break;
        }
    }

    public void end(Action action) {
        switch (action) {
            case TRAIN:
            case RECOGNIZE:
                log.debug("Gesture stop");
                gestureDevice.stop();
                break;
        }
    }

    public void feedback(Feedback feedback) {
        if (wiimote != null && active()) {
            log.debug("Wiimote rumble feedback");
            wiimote.rumble(RUMBLE);
        }
    }

    /* Connectivity */
    public void connect() throws ActivateException {
        wiimote = null;
        try {
            wiimote = wiimoteService.getDevice(this);
            ledWorker.activate();
        } catch (DeviceNotFoundException e) {
            wiimoteDiscovery.activate();
        }
    }

    public void connected() {
        try {
            wiimote = wiimoteService.getDevice(this);
            try {
                wiimoteDiscovery.deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        } catch (DeviceNotFoundException e) {
            log.error(e);
        }
    }

    public void disconnect() {
        wiimote.disconnect();
        wiimote = null;
    }

    public void disconnected() {
        try {
            wiimoteDiscovery.activate();
        } catch (ActivateException e) {
            log.error(e);
        }
    }

    /* Listeners */
    public void onButtonsEvent(WiimoteButtonsEvent event) {
        int pressed = event.getButtonsJustPressed() - event.getButtonsHeld();
        int released = event.getButtonsJustReleased();
        try {
            if (pressed != 0 && released == 0) {
                Button button = WiimoteButton.create(pressed);
                log.trace("Press: " + button);
                add(new Press(button));
            } else if (pressed == 0 && released != 0) {       
                Button button = WiimoteButton.create(released);
                log.trace("Release: " + button);
                add(new Release(button));            
            }
        } catch (UnknownButtonException e) {}
    }

    public void onMotionSensingEvent(MotionSensingEvent event) {
        gestureDevice.add(event.getGforce());
    }

    public void gestureReceived(GestureEvent event) {
        if (event.isValid()) {
            System.out.printf("id #%d, prob %.0f%%, valid %b\n", event.getId(), 100 * event.getProbability(), event.isValid());
        }
    }
    
    class LedWorker extends Worker {
        protected ArrayCycle<Integer> ledCycle;

        public LedWorker() {
            ledCycle = new ArrayCycle<Integer>();
            ledCycle.add(3);
            ledCycle.add(6);
            ledCycle.add(12);
            ledCycle.add(8);
            ledCycle.add(12);
            ledCycle.add(6);
            ledCycle.add(3);
        }

        public void deactivate() throws DeactivateException {
            super.deactivate();
            setLeds(1);
        }

        protected void work() {
            setLeds(ledCycle.next()); 
        }

        protected void setLeds(int leds) {
            wiimote.setLeds(
                (leds & 1) > 0,
                (leds & 2) > 0,
                (leds & 4) > 0,
                (leds & 8) > 0);
                sleep(leds == 8 ? 200 : 100);
        }
    }
}
