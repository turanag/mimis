package pm.device.wiimote;

import pm.Action;
import pm.Device;
import pm.Target;
import pm.exception.MacroException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.event.Press;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

public class WiimoteDevice extends Device {
    protected static final int CONNECT_MAX = 10;

    protected static WiimoteService wiimoteService;

    protected Wiimote wiimote;

    public WiimoteDevice() {
        WiimoteDevice.wiimoteService = new WiimoteService();
    }

    public void initialise() throws DeviceInitialiseException {
        wiimote = wiimoteService.getDevice(this);
        try {
            add(
                new Press(WiimoteButton.A),
                Action.TEST.setTarget(Target.APPLICATION));
        } catch (MacroException e) {
            e.printStackTrace();
        }
    }

    public void onButtonsEvent(WiimoteButtonsEvent event) {
        //evm.macroEvent(event, getWiimote(event));
    }
}
