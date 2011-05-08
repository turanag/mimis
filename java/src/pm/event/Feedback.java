package pm.event;

import pm.Event;
import pm.value.Target;

public class Feedback extends Event {
    public Feedback() {
        super(Target.ALL);
    }    
}