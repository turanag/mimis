package pm.action;

import java.lang.reflect.Method;

import pm.exception.action.NotImplementedActionException;

public enum Actions {
    START ("start"),
    TEST ("test"),
    EXIT ("exit");

    protected String action;
    protected Targets target;

    Actions(String action) {
        this.action = action;
    }

    public void setTarget(Targets target) {
        this.target = target;        
    }

    public Targets getTarget() {
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
