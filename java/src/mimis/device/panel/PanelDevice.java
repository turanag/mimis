package mimis.device.panel;

import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.input.state.Press;
import mimis.input.state.Release;
import mimis.parser.ParserInput;
import mimis.value.Action;
import mimis.worker.Component;

public class PanelDevice extends Component {
    protected static final String TITLE = "Panel";
    protected Panel panel;
    protected PanelTaskMapCycle taskMapCycle;

    public PanelDevice() {
        super(TITLE);
        taskMapCycle = new PanelTaskMapCycle();
    }

    protected void activate() throws ActivateException {
        panel = new Panel(this);
        route(new ParserInput(Action.ADD, taskMapCycle.player));
        super.activate();
    }

    public boolean active() {
        return active = panel != null;
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        panel.dispose();
        panel = null;
    }

    public void buttonPressed(PanelButton panelButton) {
        route(new Press(panelButton));
    }

    public void buttonReleased(PanelButton panelButton) {
        route(new Release(panelButton));
    }
}
