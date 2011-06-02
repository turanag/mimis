package mimis.event;

import mimis.Event;
import mimis.value.Target;

public class Feedback extends Event {
    public Feedback() {
        super(Target.ALL);
    }    
}