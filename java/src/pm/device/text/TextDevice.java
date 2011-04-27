package pm.device.text;

import java.io.InputStream;
import java.util.Scanner;

import pm.Device;
import pm.Listener;
import pm.event.EventManager;
import pm.event.Task;
import pm.value.Action;
import pm.value.Target;

public class TextDevice extends Device {
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

    public void add(String string) {
        EventManager.add(new Task(Action.valueOf(string), Target.APPLICATION));
    }
    
    public class InputListener extends Listener {
        protected Scanner input;

        public InputListener(InputStream inputStream) {
            input = new Scanner(inputStream);
        }

        public void run() {
            run = true;
            while (run && input.hasNext()) {
                String string = input.next().toUpperCase();
                if(string != null) {
                    try {
                        add(string);
                    } catch(IllegalArgumentException e) {}
                }
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}