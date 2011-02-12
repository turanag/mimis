package pm.application.example;

import pm.Action;
import pm.Application;

public class ExampleApplication extends Application {
    protected void action(Action action) {
        System.out.println("ExampleApplication: " + action);
    }
}