package mimis.event;

import mimis.Event;
import mimis.value.Target;

public class Feedback extends Event {
    protected static final long serialVersionUID = 1L;
    
    public Feedback() {
        super(Target.ALL);
    }    
}