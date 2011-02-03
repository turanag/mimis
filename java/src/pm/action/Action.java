package pm.action;

import java.lang.reflect.Method;

import pm.exception.ActionNotImplementedException;

public enum Action {
    START ("start"),
    TEST ("test"),
    EXIT ("exit");

    protected String action;

    Action(String action) {
        this.action = action;
    }

    public Method getMethod(Object object) throws ActionNotImplementedException {
        try {
            return object.getClass().getMethod(action);
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e) {}
        throw new ActionNotImplementedException();
    }
}
