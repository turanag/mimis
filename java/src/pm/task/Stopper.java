package pm.task;

import pm.event.Task;

public class Stopper extends Task {
    protected Continuous continuous;

    public Stopper(Continuous continuous) {
        super(null, null);
        this.continuous = continuous;
    }

    public void stop() {
        continuous.stop(); // Todo: check if the task is really started?
    }
}
