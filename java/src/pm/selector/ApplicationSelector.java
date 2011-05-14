package pm.selector;

import java.util.ArrayList;

import pm.Application;

public class ApplicationSelector extends Selector<Application> {

    protected static final long serialVersionUID = 1L;

    protected final static String TITLE = "MIMIS Application Selector";    
    
    public ApplicationSelector(ArrayList<Application> items) {
        super(items);
    }
}