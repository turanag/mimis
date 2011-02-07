package pm.device.javainput.extreme3d;

import pm.Macro;
import pm.action.Actions;
import pm.action.Targets;
import pm.device.javainput.JavaInputDevice;
import pm.exception.DeviceException;
import pm.exception.MacroException;
import pm.macro.event.Hold;
import pm.macro.event.Press;
import pm.macro.event.Release;

public class Extreme3DDevice extends JavaInputDevice {

    protected static final String NAME = "Logitech Extreme 3D";

    public Extreme3DDevice() throws DeviceException {
        super(NAME);
    }

    public void start() {
        super.start();
        try {
            macroListener.add(
                new Macro(
                    new Hold(Extreme3DButton.ONE),
                    new Press(Extreme3DButton.TWO),
                    new Press(Extreme3DButton.ELEVEN),
                    new Release(Extreme3DButton.ONE)),
                Actions.EXIT,
                Targets.MAIN);
        } catch (MacroException e) {
            e.printStackTrace();
        }
    }
}
