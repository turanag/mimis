package pm.device.textinput;

import java.util.Scanner;

import pm.Action;
import pm.Device;
import pm.Target;
import pm.Task;
import pm.task.TaskGatherer;

public class TextDevice extends Device implements Runnable {
    static final int SLEEP = 50;
   
    protected boolean run;
    protected Scanner input;
    
    public TextDevice() {
        initialise();
        run = true;
        new Thread(this).start();
    }
    
    public void initialise() {
        input = new Scanner(System.in);
    }
    
    public void run() {
        System.out.println("TextDevice activated");
        while(run) {
            String textinput = input.next().toUpperCase();
            if(textinput != null) {
                System.out.println(textinput);
                try {
                    TaskGatherer.add(
                            new Task(Action.valueOf(textinput), Target.APPLICATION));
                } catch(IllegalArgumentException e) { }
            }
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
