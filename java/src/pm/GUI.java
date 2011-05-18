package pm;

import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GUI extends JFrame {
    protected Log log = LogFactory.getLog(getClass());
    protected static final long serialVersionUID = 1L;

    protected static final String TITLE = "Mimis GUI";
    protected static final String APPLICATION_TITLE = "Applications";
    protected static final String DEVICE_TITLE = "Devices";
    
    public GUI(Application[] applicationArray, Device[] deviceArray) {
        super(TITLE);
        setLayout(new GridLayout(0, 1));
        //add(new JSeparator());
        JPanel controlPanel = createControlPanel(applicationArray, deviceArray);
        JPanel feedbackPanel = createFeedbackPanel();
        add(controlPanel);
        add(feedbackPanel);
        setResizable(false);
        setVisible(true);
        pack();
    }

    protected JPanel createControlPanel(Application[] applicationArray, Device[] deviceArray) {
        JPanel controlPanel = new JPanel(new GridLayout(1, 0));
        Selector<Application> applicationSelector = new Selector<Application>(APPLICATION_TITLE);
        for (Application application : applicationArray) {
            applicationSelector.add(application, application.title());
        }
        controlPanel.add(applicationSelector);
        
        
        Selector<Device> deviceSelector = new Selector<Device>(DEVICE_TITLE);
        for (Device device : deviceArray) {
            deviceSelector.add(device, device.title());
        }
        controlPanel.add(deviceSelector);
        
        return controlPanel;
    }
    
    protected JPanel createFeedbackPanel() {
        JPanel feedbackPanel = new JPanel();
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        feedbackPanel.add(textArea);
        return feedbackPanel;
    }
}
