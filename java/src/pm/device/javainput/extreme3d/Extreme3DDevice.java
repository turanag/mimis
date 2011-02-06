package pm.device.javainput.extreme3d;

import de.hardcode.jxinput.Button;
import de.hardcode.jxinput.Directional;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

import pm.device.javainput.JavaInputDevice;
import pm.exception.DeviceException;
import pm.exception.EventException;
import pm.exception.event.UnknownDirectionException;

public class Extreme3DDevice extends JavaInputDevice {

    protected static final String NAME = "Logitech Extreme 3D";

    public Extreme3DDevice() throws DeviceException {
        super(NAME);
    }

    public void start() {
        super.start();
        /*macroListener.addMacro(
            new Macro(
                new Hold(Button.A),
                new Press(Button.B),
                new Press(Button.TWO),
                new Release(Button.A)),
            Action.EXIT, Target.APPLICATION));*/
    }

    public void processEvent(JXInputAxisEvent event) {
        //addAction(Action.START, Target.APPLICATION);
        //System.out.println(event);
    }

    public void processEvent(JXInputButtonEvent event) throws EventException {
        //addAction(Action.TEST, Target.APPLICATION);
        if (event.getButton().getState()) {
            // press
        } else {
            // release
        }
        System.out.println(Extreme3DButton.create(event));
        //System.out.println(button.getType() + " " + button.getName() +" " + );
    }

    public void processEvent(JXInputDirectionalEvent event) throws UnknownDirectionException {
        //addAction(Action.EXIT, Target.APPLICATION);
        //Directional directional = event.getDirectional();
        if (event.getDirectional().isCentered()) {
            // release
        } else {
            // press
        }
        System.out.println(Extreme3DDirection.create(event));
        //System.out.println(Extreme3DButton.create(event));
        //System.out.println(directional.isCentered() + " " + directional.getValue() + " [" + directional.getName() + "] " + directional.getResolution() + " " + directional.getDirection());
    }

}
