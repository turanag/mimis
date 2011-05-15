package pm.device.wiimote;

import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;
import org.wiigee.util.Log;

import pm.Button;
import pm.Device;
import pm.device.wiimote.gesture.GestureDevice;
import pm.event.Feedback;
import pm.event.Task;
import pm.exception.button.UnknownButtonException;
import pm.exception.device.DeviceNotFoundException;
import pm.macro.state.Hold;
import pm.macro.state.Press;
import pm.macro.state.Release;
import pm.value.Action;
import wiiusej.Wiimote;
import wiiusej.values.Calibration;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

public class WiimoteDevice extends Device implements GestureListener {
    protected static final String TITLE = "Wiimote";

    protected static final int CONNECT_MAX = 10;
    protected static final int RUMBLE = 150;

    protected static WiimoteService wiimoteService;

    protected Wiimote wiimote;
    protected Calibration calibration;
    protected GestureDevice gestureDevice;
    protected int gestureId = 0;

    static {
        WiimoteDevice.wiimoteService = new WiimoteService();
        Log.setLevel(0);
    }

    public WiimoteDevice() {
        super(TITLE);
        gestureDevice = new GestureDevice();
        gestureDevice.add(this);
    }

    public void initialise() {
        add(
            new Hold(WiimoteButton.A),
            new Task(Action.TRAIN),
            new Task(Action.STOP));
        add(
            new Press(WiimoteButton.B),
            new Task(Action.SAVE));
        add(
            new Press(WiimoteButton.DOWN),
            new Task(Action.LOAD));
        add(
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

    public void onButtonsEvent(WiimoteButtonsEvent event) {
        int pressed = event.getButtonsJustPressed() - event.getButtonsHeld();
        int released = event.getButtonsJustReleased();
        try {
            if (pressed != 0 && released == 0) {
                Button button = WiimoteButton.create(pressed);
                System.out.println("Press: " + button);
                add(new Press(button));
            } else if (pressed == 0 && released != 0) {       
                Button button = WiimoteButton.create(released);
                System.out.println("Release: " + button);
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
    
    public void feedback(Feedback feedback) {
        System.out.println("Wiimote feedback");
        wiimote.rumble(RUMBLE);
    }

    public void activate() {
        try {
            wiimote = wiimoteService.getDevice(this);
        } catch (DeviceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        wiimote.activateMotionSensing();        
    }

    public void deactivate() {
        wiimote.deactivateMotionSensing();        
    }
    
    public void exit() {
        wiimoteService.exit();
        super.exit();
    }
}
