package pm.device.javainput.extreme3d;

import de.hardcode.jxinput.Button;
import de.hardcode.jxinput.Directional;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

import pm.action.Action;
import pm.device.javainput.JavaInputDevice;
import pm.device.macro.Macro;
import pm.event.Target;
import pm.exception.DeviceException;

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

    public void processEvent(JXInputButtonEvent event) {
        //addAction(Action.TEST, Target.APPLICATION);
        Button button = event.getButton();
        Extreme3DButton x = null;
        try {
            x = Extreme3DButton.create(button.getName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(x);
        //System.out.println(button.getType() + " " + button.getName() +" " + event.getButton().getState());
    }

    public void processEvent(JXInputDirectionalEvent event) {
        //addAction(Action.EXIT, Target.APPLICATION);
        Directional directional = event.getDirectional();
        
        System.out.println(directional.getValue() + " [" + directional.getName() + "] " + directional.getResolution() + " " + directional.getDirection());
    }

}
