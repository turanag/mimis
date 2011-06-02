package mimis;

import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GUI extends JFrame {
    protected Log log = LogFactory.getLog(getClass());
    protected static final long serialVersionUID = 1L;

    protected static final String TITLE = "Mimis GUI";
    protected static final String APPLICATION_TITLE = "Applications";
    protected static final String DEVICE_TITLE = "Devices";
    
    protected Mimis mimis;

    public GUI(Mimis mimis, Manager<Application> applicationManager, Manager<Device> deviceManager) {
        super(TITLE);
        this.mimis = mimis;
        createFrame(applicationManager, deviceManager);
    }

    protected void createFrame(Manager<Application> applicationManager, Manager<Device> deviceManager) {
        setLayout(new GridLayout(0, 1));
        JPanel controlPanel = createControlPanel(applicationManager, deviceManager);
        add(controlPanel);
        JPanel feedbackPanel = createFeedbackPanel();
        add(feedbackPanel);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
    }

    protected JPanel createControlPanel(Manager<Application> applicationManager, Manager<Device> deviceManager) {
        JPanel controlPanel = new JPanel(new GridLayout(1, 0));
        JPanel applicationPanel = createManagerPanel(applicationManager, APPLICATION_TITLE);
        controlPanel.add(applicationPanel);
        JPanel devicePanel = createManagerPanel(deviceManager, DEVICE_TITLE);
        controlPanel.add(devicePanel);
        return controlPanel;
    }

    protected JPanel createManagerPanel(Manager<?> manager, String title) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel(title, SwingConstants.CENTER));
        for (JToggleButton button : manager.getButtons()) {
            panel.add(button);
        }
        return panel;
    }

    protected JPanel createFeedbackPanel() {
        JPanel feedbackPanel = new JPanel();
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        feedbackPanel.add(textArea);
        return feedbackPanel;
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            log.debug("Window closing");
            exit();
            mimis.exit();
        }
    }

    protected void exit() {
        log.debug("Dispose");
        dispose();
    }
}
