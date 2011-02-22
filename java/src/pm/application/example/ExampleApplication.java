package pm.application.example;

import pm.Application;
import pm.value.Action;

public class ExampleApplication extends Application {
    protected void action(Action action) {
        System.out.println("ExampleApplication: " + action);
    }
}