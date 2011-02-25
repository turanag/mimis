package pm.device.text;

import java.io.InputStream;
import java.util.Scanner;

import pm.Listener;
import pm.event.Task;
import pm.event.EventManager;
import pm.exception.task.TaskNotSupportedException;
import pm.value.Action;
import pm.value.Target;

public class InputListener extends Listener {
    protected Scanner input;

    public InputListener(InputStream inputStream) {
        input = new Scanner(inputStream);
    }

    public void run() {
        run = true;
        while (running()) {
            String string = input.next().toUpperCase();
            if(string != null) {
                try {
                    EventManager.add(
                        new Task(Action.valueOf(string), Target.APPLICATION));
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
}
