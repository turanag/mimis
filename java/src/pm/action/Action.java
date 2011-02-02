package pm.action;

import java.lang.reflect.Method;

public enum Action {
    START ("start"),
    TEST ("test");

    protected String action;

    Action(String action) {
        this.action = action;
    }

    public Method getMethod(Object object) throws Exception {
        return object.getClass().getMethod(action);
    }
}
