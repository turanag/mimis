package mimis.application;

import mimis.device.lirc.button.ColorButton;
import mimis.device.wiimote.WiimoteButton;
import mimis.exception.worker.ActivateException;
import mimis.input.Input;
import mimis.input.Task;
import mimis.input.state.Hold;
import mimis.input.state.Press;
import mimis.input.state.Release;
import mimis.input.state.sequence.Sequence;
import mimis.parser.ParserInput;
import mimis.state.TaskMap;
import mimis.value.Action;
import mimis.value.Target;
import mimis.worker.Component;

public class TestApplication extends Component {
    public TestApplication() {
        super("Test App");
    }

    public void activate() throws ActivateException {
        TaskMap taskMap = new TaskMap();
        taskMap.add(new Sequence(
                new Hold(ColorButton.BLUE), new Press(WiimoteButton.A), new Release(ColorButton.BLUE)),
                new Task(Action.TEST, Target.CURRENT));
        route(new ParserInput(Action.ADD, taskMap));

        listen(Task.class);

        super.activate();
    }

    public void test() {
        while (!active());

        route(new Press(ColorButton.BLUE));
        /*sleep(1000);
        route(new ParserInput(Action.RESET, this, false));*/
        sleep(1000);
        route(new Press(WiimoteButton.A));
    }

    public void input(Input input) {
        if (input instanceof Task) {
            Task task = (Task) input;
            log.debug(task.getAction() + " " + task.getSignal() + " " + task.getTarget());
        } else {
            log.debug(input.getClass());
        }
    }
}
