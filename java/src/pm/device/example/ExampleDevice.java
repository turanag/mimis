package pm.device.example;

import pm.action.Action;
import pm.device.Device;
import pm.event.Target;

public class ExampleDevice extends Device {
    public void start() {
       System.out.println("Ik hoef niets te starten");
       addAction(Action.START, Target.APPLICATION);
       addAction(Action.TEST, Target.APPLICATION);
       addAction(Action.EXIT, Target.MAIN);
    }

    public void exit() {
        
    }    
}
