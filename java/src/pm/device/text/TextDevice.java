package pm.device.text;

import pm.Device;

public class TextDevice extends Device implements Runnable {
    InputListener inputListener;

    public TextDevice() {
        inputListener = new InputListener(System.in);
    }

    public void initialise() {
        inputListener.start();
    }

    public void exit() {
        inputListener.stop();
    }
}
