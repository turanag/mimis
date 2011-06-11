package mimis.device;

import mimis.sequence.EventMap;
import mimis.util.ArrayCycle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EventMapCycle extends ArrayCycle<EventMap> {
    protected static final long serialVersionUID = 1L;
    
    protected Log log = LogFactory.getLog(getClass());
}
