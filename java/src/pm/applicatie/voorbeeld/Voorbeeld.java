package pm.applicatie.voorbeeld;

import pm.action.Action;
import pm.application.Application;

public class Voorbeeld extends Application {
    public Voorbeeld() {
        try {
            invoke(Action.TEST);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("jeheee!");
    }

    public void test() {
        System.out.println("neheee!");
    }
}
