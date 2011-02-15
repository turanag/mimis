package pm.device.wiimote;

import org.wiigee.event.GestureEvent;
import org.wiigee.event.GestureListener;
import org.wiigee.util.Log;

import pm.Action;
import pm.Button;
import pm.Device;
import pm.Target;
import pm.Task;
import pm.device.wiimote.gesture.GestureDevice;
import pm.exception.device.DeviceInitialiseException;
import pm.exception.event.UnknownButtonException;
import pm.macro.event.Hold;
import pm.macro.event.Press;
import pm.macro.event.Release;

import wiiusej.Wiimote;
import wiiusej.values.Acceleration;
import wiiusej.values.Calibration;
import wiiusej.values.RawAcceleration;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

public class WiimoteDevice extends Device implements GestureListener {
    protected static final int CONNECT_MAX = 10;

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
        gestureDevice = new GestureDevice();
        gestureDevice.add(this);
    }

    public void initialise() throws DeviceInitialiseException {
        wiimote = wiimoteService.getDevice(this);
        wiimote.activateMotionSensing();
        add(
            new Hold(WiimoteButton.A),
            new Task(Action.TRAIN, Target.DEVICE),
            new Task(Action.STOP, Target.DEVICE));
        add(
            new Press(WiimoteButton.B),
            new Task(Action.SAVE, Target.DEVICE));
        add(
            new Press(WiimoteButton.DOWN),
            new Task(Action.LOAD, Target.DEVICE));
        add(
            new Hold(WiimoteButton.HOME),
            new Task(Action.RECOGNIZE, Target.DEVICE),
            new Task(Action.STOP, Target.DEVICE));
    }

    public void exit() {
        wiimote.deactivateMotionSensing();
        wiimoteService.exit();
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
        if (calibration == null) {
            calibration = wiimote.getCalibration();
        }
        RawAcceleration rawAcceleration = event.getRawAcceleration();
        Acceleration acceleration = calibration.getAcceleration(rawAcceleration);
        //System.out.println(event);
        gestureDevice.add(acceleration.toArray());
    }

    public void gestureReceived(GestureEvent event) {
        if (event.isValid()) {
            System.out.printf("id #%d, prob %.0f%%, valid %b\n", event.getId(), 100 * event.getProbability(), event.isValid());
        }
    }
}
