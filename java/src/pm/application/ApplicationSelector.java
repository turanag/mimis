package pm.application;

import pm.Application;
import pm.Selector;

public class ApplicationSelector extends Selector<Application> {
    protected static final long serialVersionUID = 1L;

    protected final static String TITLE = "MIMIS Application Selector";    

    public ApplicationSelector(Application[] applicationArray) {
        super(applicationArray);
    }
}