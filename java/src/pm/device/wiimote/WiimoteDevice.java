package pm.device.wiimote;

import pm.Action;
import pm.Button;
import pm.Device;
import pm.Target;
import pm.Task;
import pm.exception.device.DeviceInitialiseException;
import pm.exception.event.UnknownButtonException;
import pm.macro.event.Press;
import pm.macro.event.Release;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

public class WiimoteDevice extends Device {
    protected static final int CONNECT_MAX = 10;

    protected static WiimoteService wiimoteService;
    protected static GestureService gestureService;

    protected Wiimote wiimote;

    {
        WiimoteDevice.wiimoteService = new WiimoteService();
        WiimoteDevice.gestureService = new GestureService();
    }

    public void initialise() throws DeviceInitialiseException {
        wiimote = wiimoteService.getDevice(this);
        wiimote.activateMotionSensing();
        add(
            new Press(WiimoteButton.A),
            new Task(Action.TEST, Target.APPLICATION));
    }

    public void exit() {
        wiimoteService.exit();
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
        //System.out.println(event.getRawAcceleration());
    }
}
