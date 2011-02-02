package pm.application;

import pm.action.Action;

public abstract class Application {
    public void invoke(Action action) {
        switch (action) {
            case START:
                start();
                break;
        }
    }

    protected abstract void start();
}
