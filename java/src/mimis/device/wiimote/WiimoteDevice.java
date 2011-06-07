package mimis.device.wiimote;

import mimis.Button;
import mimis.Device;
import mimis.device.wiimote.gesture.GestureDevice;
import mimis.event.Feedback;
import mimis.event.Task;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.device.DeviceNotFoundException;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.sequence.state.Press;
import mimis.sequence.state.Release;
import mimis.value.Action;

import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;
import org.wiigee.util.Log;

import wiiusej.Wiimote;
import wiiusej.values.Calibration;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

public class WiimoteDevice extends Device implements GestureListener {
    protected static final String TITLE = "Wiimote";

    protected static final int CONNECT_MAX = 10;
    protected static final int RUMBLE = 150;
    public static final int TIMEOUT = 200;

    protected static WiimoteService wiimoteService;

    protected Wiimote wiimote;
    protected Calibration calibration;
    protected GestureDevice gestureDevice;
    protected int gestureId;
    protected WiimoteDiscovery wiimoteDiscovery;
    protected boolean connected;

    static {
        WiimoteDevice.wiimoteService = new WiimoteService();
        Log.setLevel(0);
    }

    public WiimoteDevice() {
        super(TITLE);
        wiimoteDiscovery = new WiimoteDiscovery(this);
        gestureDevice = new GestureDevice();
        gestureDevice.add(this);
        gestureId = 0;
    }

    /* Activation */
    public void activate() throws ActivateException {
        super.activate();
        connect();
        /*add(
            new Hold(WiimoteButton.A),
            new Task(Action.TRAIN),
            new Task(Action.STOP));*/
        add(
            new Press(WiimoteButton.B),
            new Task(Action.SAVE));
        add(
            new Press(WiimoteButton.DOWN),
            new Task(Action.LOAD));
        /*add(
            new Hold(WiimoteButton.HOME),
            new Task(Action.RECOGNIZE),
            new Task(Action.STOP));/*
        add(
            new Press(WiimoteButton.A),
            new Task(Target.APPLICATION, Action.PLAY));
        add(
            new Press(WiimoteButton.B),
            new Task(Target.APPLICATION, Action.MUTE));
        add(
            new Press(WiimoteButton.ONE),
            new Task(Target.APPLICATION, Action.SHUFFLE));
        add(
            new Press(WiimoteButton.TWO),
            new Task(Target.APPLICATION, Action.REPEAT));
        add(
            new Press(WiimoteButton.UP),
            new Task(Target.APPLICATION, Action.NEXT));
        add(
            new Press(WiimoteButton.DOWN),
            new Task(Target.APPLICATION, Action.PREVIOUS));
        add(
            new Hold(WiimoteButton.RIGHT),
            new Dynamic(Action.FORWARD, Target.APPLICATION, 200, -30));
        add(
            new Hold(WiimoteButton.LEFT),
            new Dynamic(Action.REWIND, Target.APPLICATION, 200, -30));
        add(
            new Hold(WiimoteButton.MINUS),
            new Continuous(Action.VOLUME_DOWN, Target.APPLICATION, 100));
        add(
            new Hold(WiimoteButton.PLUS),
            new Continuous(Action.VOLUME_UP, Target.APPLICATION, 100));
        add(
            new Press(WiimoteButton.HOME),
            new Task(Target.MANAGER, Action.NEXT));
        try {
            add(
                new Macro(
                    new Hold(WiimoteButton.TWO),
                    new Press(WiimoteButton.PLUS),
                    new Release(WiimoteButton.TWO)),
                new Task(Target.APPLICATION, Action.LIKE));
            add(
                new Macro(
                    new Hold(WiimoteButton.TWO),
                    new Press(WiimoteButton.MINUS),
                    new Release(WiimoteButton.TWO)),
                new Task(Target.APPLICATION, Action.DISLIKE));
        } catch (StateOrderException e) {}*/
    }
    
    public void deactivate() throws DeactivateException {
        super.deactivate();
        if (wiimote != null) {
            wiimote.deactivateMotionSensing();
        }
        wiimoteDiscovery.disconnect();
    }

    public void stop() {
        super.stop();
        wiimoteService.exit();
    }

    /* Events */
    public void action(Action action) {
        switch (action) {
            case TRAIN:
                System.out.println("Wiimote Train");
                gestureDevice.train();
                break;
            case STOP:
                System.out.println("Wiimote Stop");
                gestureDevice.stop();
                break;
            case SAVE:
                System.out.println("Wiimote Save");
                gestureDevice.close();
                gestureDevice.saveGesture(gestureId, "C:\\gesture-" + gestureId);
                ++gestureId;
                break;
            case LOAD:
                for (int i = 0; i < gestureId; ++i) {
                    gestureDevice.loadGesture("C:\\gesture-" + i);
                }
                break;
            case RECOGNIZE:
                gestureDevice.recognize();
                break;
        }
    }

    public void feedback(Feedback feedback) {
        System.out.println("Wiimote feedback");
        wiimote.rumble(RUMBLE);
    }

    /* Connectivity */
    public void connect() {
        wiimote = null;
        try {
            wiimote = wiimoteService.getDevice(this);
            super.activate();
        } catch (DeviceNotFoundException e) {
            log.error(e);
        } catch (ActivateException e) {
            log.error(e);
        }
        if (wiimote == null) {
            try {
                wiimoteDiscovery.activate();
            } catch (ActivateException e) {
                log.error(e);
            }
        }
    }

    public void connected() {
        try {
            wiimote = wiimoteService.getDevice(this);
            //wiimote.activateMotionSensing();
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
        wiimoteDiscovery.disconnect();
    }

    public void disconnected() {
        try {
            wiimoteDiscovery.activate();
        } catch (ActivateException e) {
            log.error(e);
        }
    }

    public boolean active() {
        if (wiimote != null) {
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
                disconnect();
                active = false;
            }
        }
        return active;
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
}
