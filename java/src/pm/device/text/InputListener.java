package pm.device.text;

import java.io.InputStream;
import java.util.Scanner;

import pm.Task;
import pm.task.TaskManager;
import pm.value.Action;
import pm.value.Target;

public class InputListener implements Runnable {
    protected static final int SLEEP = 100;
    
    protected boolean run;
    protected Scanner input;

    public InputListener(InputStream inputStream) {
        input = new Scanner(inputStream);
    }

    public void start() {
        new Thread(this).start();
    }

    public void run() {
        run = true;
        while (running()) {
            String textinput = input.next().toUpperCase();
            if(textinput != null) {
                try {
                    TaskManager.add(
                        new Task(Action.valueOf(textinput), Target.APPLICATION));
                } catch(IllegalArgumentException e) {}
            }
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean running() {
        return run && input.hasNext();
    }

    public void stop() {
        run = false;        
    }
}
