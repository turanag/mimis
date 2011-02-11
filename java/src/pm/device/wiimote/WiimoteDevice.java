package pm.device.wiimote;

import pm.Action;
import pm.Target;
import pm.device.Device;
import pm.exception.DeviceException;
import pm.exception.MacroException;
import pm.macro.event.Press;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

public class WiimoteDevice extends Device {
    protected static final int CONNECT_MAX = 10;

    protected static WiimoteService wiimoteService;

    protected Wiimote wiimote;

    {
        WiimoteDevice.wiimoteService = new WiimoteService();
    }

    public WiimoteDevice() throws DeviceException {
        wiimote = wiimoteService.getDevice(this);
    }

    public void initialise() {
        super.initialise();
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
