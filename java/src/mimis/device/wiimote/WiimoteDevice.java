package mimis.device.wiimote;

import mimis.Button;
import mimis.device.wiimote.gesture.GestureDevice;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.device.DeviceNotFoundException;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.input.Feedback;
import mimis.input.state.Press;
import mimis.input.state.Release;
import mimis.parser.ParserInput;
import mimis.util.ArrayCycle;
import mimis.value.Action;
import mimis.worker.Component;
import mimis.worker.Worker;

import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;
import org.wiigee.util.Log;

import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

public class WiimoteDevice extends Component implements GestureListener {
    protected static final String TITLE = "Wiimote";
    protected static final int RUMBLE = 50;
    protected static final int CONNECTED_TIMEOUT = 500;
    protected static final int LED_TIMEOUT = 1000;
    protected static final int LED_SLEEP = 50;

    protected static WiimoteService wiimoteService;
    protected WiimoteTaskMapCycle taskMapCycle;
    protected WiimoteDiscovery wiimoteDiscovery;
    protected Wiimote wiimote;
    protected boolean connected;
    protected GestureDevice gestureDevice;
    protected int gestureId;
    protected LedWorker ledWorker;
    protected boolean disconnect;

    static {
        WiimoteDevice.wiimoteService = new WiimoteService();
        Log.setLevel(Log.DEBUG);
    }

    public WiimoteDevice() {
        super(TITLE);
        taskMapCycle = new WiimoteTaskMapCycle();
        wiimoteDiscovery = new WiimoteDiscovery(this);
        gestureDevice = new GestureDevice();
        gestureDevice.add(this);
        gestureId = 0;
        ledWorker = new LedWorker();
    }

    /* Worker */
    protected void activate() throws ActivateException {
        add(taskMapCycle.player);
        wiimote = null;
        try {
            connect();
        } catch (DeviceNotFoundException e) {
            wiimoteDiscovery.start();
        }
        super.activate();
    }

    public synchronized boolean active() {
        if (wiimote != null) {
            connected = false;
            wiimote.getStatus();
            try {
                wait(CONNECTED_TIMEOUT);
            } catch (InterruptedException e) {
                log.error(e);
            }
            if (!connected) {
                try {
                    connect();
                } catch (DeviceNotFoundException e) {
                    disconnect = true;
                    stop();
                }
            }
        }
        return active;
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        ledWorker.stop();
        wiimoteDiscovery.stop();
        if (disconnect) {
            if (wiimote != null) {
                wiimote.disconnect();
            }
            wiimoteDiscovery.disconnect();
            disconnect = false;
        }
    }

    public void exit() {
        super.exit();
        ledWorker.exit();
        if (wiimote != null) {
            wiimote.disconnect();
            wiimote = null;
        }
        wiimoteService.exit();
        wiimoteDiscovery.exit();
    }

    /* Events */
    public void begin(Action action) {
        switch (action) {
            case SHIFT:
                log.debug("Shift");
                route(new ParserInput(Action.RESET, taskMapCycle.player));
                add(taskMapCycle.mimis);
                add(taskMapCycle.like);
                break;
            case UNSHIFT:
                log.debug("Unshift");
                route(new ParserInput(Action.RESET, taskMapCycle.mimis));
                route(new ParserInput(Action.RESET, taskMapCycle.like));
                add(taskMapCycle.player);
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
            //log.debug("Wiimote rumble feedback");
            //wiimote.rumble(RUMBLE);
        }
    }

    /* Connectivity */
    public synchronized void connect() throws DeviceNotFoundException {
        wiimote = wiimoteService.getDevice(this);
        //wiimote.activateContinuous();
        wiimoteDiscovery.stop();
        ledWorker.start();
        //sleep(10000);
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

    public void onIrEvent(IREvent event) {
        
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
            ledCycle.add(1);
            ledCycle.add(3);
            ledCycle.add(6);
            ledCycle.add(12);
            ledCycle.add(8);
            ledCycle.add(12);
            ledCycle.add(6);
            ledCycle.add(3);
            ledCycle.add(1);
        }

        public void activate() throws ActivateException {
            sleep(LED_TIMEOUT);
            super.activate();
        }
        
        public void deactivate() throws DeactivateException {
            super.deactivate();
            setLeds(1);
        }

        protected void work() {
            setLeds(ledCycle.next());
            sleep(LED_SLEEP);
        }

        protected void setLeds(int leds) {
            if (wiimote != null) {
                wiimote.setLeds((leds & 1) > 0, (leds & 2) > 0, (leds & 4) > 0, (leds & 8) > 0);
                sleep((leds == 8 ? 2 : 1) * LED_SLEEP);
            }
        }
    }
}
