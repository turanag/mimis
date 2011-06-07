package mimis.device;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mimis.sequence.EventMap;
import mimis.util.ArrayCycle;

public class EventMapCycle extends ArrayCycle<EventMap> {
    protected static final long serialVersionUID = 1L;
    
    protected Log log = LogFactory.getLog(getClass());
}
