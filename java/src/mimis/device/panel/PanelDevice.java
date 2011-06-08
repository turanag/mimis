package mimis.device.panel;

import javax.swing.WindowConstants;

import mimis.Device;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.sequence.state.Press;
import mimis.sequence.state.Release;

public class PanelDevice extends Device {
    protected static final String TITLE = "Panel";
    protected Panel panel;
    protected PanelEventMapCycle eventMapCycle;

    public PanelDevice() {
        super(TITLE);
        eventMapCycle = new PanelEventMapCycle();
    }

    public void activate() throws ActivateException {
        super.activate();
        panel = new Panel(this);
        panel.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        add(eventMapCycle.player);
    }

    public boolean active() {
        return active = panel != null;
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        panel.dispose();
        panel = null;
    }

    public void buttonPressed(PanelButton panelButton) {
        add(new Press(panelButton));
    }

    public void buttonReleased(PanelButton panelButton) {
        add(new Release(panelButton));
    }
}
