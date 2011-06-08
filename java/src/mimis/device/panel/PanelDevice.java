package mimis.device.panel;

import java.awt.event.WindowEvent;

import javax.swing.WindowConstants;

import mimis.Device;
import mimis.exception.worker.ActivateException;
import mimis.sequence.state.Press;
import mimis.sequence.state.Release;

public class PanelDevice extends Device implements PanelButtonListener {
    protected static final String TITLE = "Panel";
    protected Panel panel;
    protected PanelEventMapCycle eventMapCycle;

    public PanelDevice() {
        super(TITLE);
        eventMapCycle = new PanelEventMapCycle();
    }

    public void activate() throws ActivateException {
        super.activate();
        panel = new Panel(this) {
            protected static final long serialVersionUID = 1L;
            protected void processWindowEvent(WindowEvent e) {
                log.debug("Window closing");
                if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                    deactivate();
                }
            }
        };
        panel.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        add(eventMapCycle.player);
    }

    public boolean active() {
        return active = panel != null && panel.isValid();
    }

    public void deactivate() {
        panel.dispose();
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            log.debug("Window closing");
            deactivate();
        }
    }

    public void buttonPressed(PanelButton panelButton) {
        add(new Press(panelButton));
    }

    public void buttonReleased(PanelButton panelButton) {
        add(new Release(panelButton));
    }
}
