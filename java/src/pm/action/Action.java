package pm.action;

import java.lang.reflect.Method;

import pm.event.Target;
import pm.exception.action.NotImplementedActionException;

public enum Action {
    START ("start"),
    TEST ("test"),
    EXIT ("exit");

    protected String action;
    protected Target target;

    Action(String action) {
        this.action = action;
    }

    public void setTarget(Target target) {
        this.target = target;        
    }

    public Target getTarget() {
        return target;
    }

    public Method getMethod(Object object) throws NotImplementedActionException {
        try {
            return object.getClass().getMethod(action);
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e) {}
        throw new NotImplementedActionException();
    }
}
