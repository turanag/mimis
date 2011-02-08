package pm.device.javainput;

import de.hardcode.jxinput.JXInputDevice;
import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputDirectionalEvent;

import pm.Button;
import pm.device.Device;
import pm.device.javainput.extreme3d.Extreme3DButton;
import pm.device.javainput.extreme3d.Extreme3DDirection;
import pm.exception.DeviceException;
import pm.exception.EventException;
import pm.exception.device.JavaInputDeviceNotFoundException;
import pm.exception.event.UnknownDirectionException;
import pm.macro.event.Press;
import pm.macro.event.Release;

public abstract class JavaInputDevice extends Device {
    protected JavaInputListener javaInputListener;
    //protected JXInputDevice jxinputDevice;
    protected Button previousDirectionalButton;

    protected JavaInputDevice(String name) throws DeviceException {
        super();
        javaInputListener = new JavaInputListener(this, getDevice(name));
   }

    public void start() {
        javaInputListener.start();
    }

    public void exit() {
        javaInputListener.exit();
    }

    public static JXInputDevice getDevice(String name) throws DeviceException {
        int numberOfDevices = JXInputManager.getNumberOfDevices();
        for (int i = 0; i < numberOfDevices; ++i) {
            JXInputDevice device = JXInputManager.getJXInputDevice(i);
            if (device.getName().startsWith(name)) {
                return device;
            }
        }
        throw new JavaInputDeviceNotFoundException();
    }

    public void processEvent(JXInputAxisEvent event) {
        //addAction(Action.START, Target.APPLICATION);
        //System.out.println(event);
    }

    public void processEvent(JXInputButtonEvent event) throws EventException {
        //addAction(Action.TEST, Target.APPLICATION);
        Button button = Extreme3DButton.create(event);
        if (event.getButton().getState()) {
            System.out.println("Press: " + button);
            add(new Press(button));
        } else {
            System.out.println("Release: " + button);
            add(new Release(button));
        }
        //System.out.println(button);
        //System.out.println(button.getType() + " " + button.getName() +" " + );
    }

    public void processEvent(JXInputDirectionalEvent event) throws UnknownDirectionException {
        //addAction(Action.EXIT, Target.APPLICATION);
        //Directional directional = event.getDirectional();
        Button button = Extreme3DDirection.create(event);
        if (event.getDirectional().isCentered()) {
            if (previousDirectionalButton != null) {
                System.out.println("Release: " + previousDirectionalButton);
                add(new Release(previousDirectionalButton));
            }
        } else {
            System.out.println("Press: " + button);
            add(new Press(button));
            previousDirectionalButton = button;
        }
        //System.out.println(Extreme3DDirection.create(event));
        //System.out.println(Extreme3DButton.create(event));
        //System.out.println(directional.isCentered() + " " + directional.getValue() + " [" + directional.getName() + "] " + directional.getResolution() + " " + directional.getDirection());
    }
}
