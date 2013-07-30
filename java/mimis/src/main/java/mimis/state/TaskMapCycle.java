package mimis.state;

import mimis.input.Input;
import mimis.state.TaskMap;
import mimis.util.ArrayCycle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TaskMapCycle extends ArrayCycle<TaskMap> implements Input {
    protected static final long serialVersionUID = 1L;
    
    protected Log log = LogFactory.getLog(getClass());
}
