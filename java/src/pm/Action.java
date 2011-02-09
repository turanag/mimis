package pm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import pm.exception.ActionException;
import pm.exception.action.InvokeActionException;
import pm.exception.action.NotImplementedActionException;
import pm.exception.action.TargetNotSetException;

public enum Action {
    START ("start"),
    TEST ("test"),
    EXIT ("exit"),
    PLAY ("play"),
    PAUSE ("pause"),
    RESUME ("resume");
    
    protected String action;
    protected Target target;

    Action(String action) {
        this.action = action;
    }

    public Action setTarget(Target target) {
        this.target = target;
        return this;
    }

    public Target getTarget() throws TargetNotSetException {
        if (target == null) {
            throw new TargetNotSetException();
        }
        return target;
    }

    public Method getMethod(Object object) throws NotImplementedActionException {
        try {
            return object.getClass().getMethod(action);
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e) {}
        throw new NotImplementedActionException();
    }

    public void invoke(Object object) throws ActionException {
        try {
            getMethod(object).invoke(object);
            return;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }        
        throw new InvokeActionException();
        // Todo: informatie doorgeven over wat er precies is foutgegaan
    }
}
