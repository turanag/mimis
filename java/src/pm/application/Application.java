package pm.application;

import pm.action.Action;

public abstract class Application {
    public void invoke(Action action) throws Exception {
        try {
            action.getMethod(this).invoke(this);
        } catch (Exception e) {
            throw new Exception("Failed to invoke action");
        }
    }

    protected abstract void start();
}
