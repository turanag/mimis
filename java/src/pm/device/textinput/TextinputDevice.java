package pm.device.textinput;

import java.util.Scanner;

import pm.device.Device;

public class TextinputDevice extends Device {
    static final int SLEEP = 50;
   
    Scanner textinputScanner;
    boolean run;
    
    public TextinputDevice() {
        textinputScanner = new Scanner(System.in);
        run = true;
    }
    
    public void initialise() {
        while(run) {
            String textinput = textinputScanner.next();
            if(textinput != null) {
                System.out.println(textinput);
            }
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
