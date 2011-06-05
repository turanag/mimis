package mimis.device.panel;

import java.awt.event.WindowEvent;

import javax.swing.WindowConstants;

import mimis.Device;
import mimis.event.Task;
import mimis.exception.worker.ActivateException;
import mimis.sequence.state.Press;
import mimis.sequence.state.Release;
import mimis.value.Action;
import mimis.value.Target;


public class PanelDevice extends Device implements PanelButtonListener {
    protected static final String TITLE = "Panel";
    protected Panel panel;

    public PanelDevice() {
        super(TITLE);
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
        //panel.updateTime(12342398);
        //panel.updatePosition(43);
        add(new Press(PanelButton.PREVIOUS), new Task(Target.APPLICATION, Action.PREVIOUS));
        add(new Press(PanelButton.REWIND), new Task(Target.APPLICATION, Action.REWIND));
        add(new Press(PanelButton.STOP), new Task(Target.APPLICATION, Action.STOP));
        add(new Press(PanelButton.PAUSE), new Task(Target.APPLICATION, Action.PAUSE));
        add(new Press(PanelButton.PLAY), new Task(Target.APPLICATION, Action.PLAY));
        add(new Press(PanelButton.FORWARD), new Task(Target.APPLICATION, Action.FORWARD));
        add(new Press(PanelButton.NEXT), new Task(Target.APPLICATION, Action.NEXT));
        add(new Press(PanelButton.VOLUME_DOWN), new Task(Target.APPLICATION, Action.VOLUME_DOWN));
        add(new Press(PanelButton.MUTE), new Task(Target.APPLICATION, Action.MUTE));
        add(new Press(PanelButton.VOLUME_UP), new Task(Target.APPLICATION, Action.VOLUME_UP));
        add(new Press(PanelButton.REPEAT), new Task(Target.APPLICATION, Action.REPEAT));
        add(new Press(PanelButton.SHUFFLE), new Task(Target.APPLICATION, Action.SHUFFLE));
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
        //Vang hier toggles af om bijvoorbeeld de play/pause en mute knop en veranderen
        add(new Press(panelButton));
    }

    public void buttonReleased(PanelButton panelButton) {
        add(new Release(panelButton));
    }
}
