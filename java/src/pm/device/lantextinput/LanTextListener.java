package pm.device.lantextinput;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import pm.Action;
import pm.Target;
import pm.Task;
import pm.task.TaskGatherer;

public class LanTextListener implements Runnable {
    static final int SLEEP = 100;
    
    protected boolean run;
    protected Socket socket;
    protected Scanner input;
    
    public LanTextListener(Socket socket){
        this.socket = socket;
        try {
            input = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        run = true;
        new Thread(this).start();
    }
       
    public void run() {
        while (run && socket.isConnected() && input.hasNext()) {
            String textinput = input.next().toUpperCase();
            if(textinput != null) {
                try {
                    TaskGatherer.add(
                        new Task(Action.valueOf(textinput), Target.APPLICATION));
                } catch (IllegalArgumentException e) {}
            }
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exit();
    }
    
    protected void exit() {
        run = false;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
