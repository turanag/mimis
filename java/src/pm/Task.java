package pm;

public class Task {
    Action action;
    Target target;

    public Task(Action action, Target target) {
        this.action = action;
        this.target = target;
    }

    public Action getAction() {
        return action;
    }

    public Target getTarget() {
        return target;
    }
}
