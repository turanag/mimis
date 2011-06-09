package mimis.device.panel;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mimis.exception.worker.DeactivateException;
import mimis.util.swing.HoldButton;
import mimis.util.swing.HoldButtonListener;
import mimis.util.swing.ToggleButton;

public class Panel extends JFrame implements HoldButtonListener {
    protected static final long serialVersionUID = 1L;
    protected Log log = LogFactory.getLog(getClass());

    protected final static String TITLE = "MIMIS Panel Device";

    protected PanelDevice panelDevice;
    protected ClassLoader classLoader;
    protected HoldButton upButton;
    protected HoldButton previousButton;
    protected HoldButton rewindButton;
    protected HoldButton stopButton;
    protected ToggleButton playPauseToggleButton;
    protected HoldButton forwardButton;
    protected HoldButton downButton;
    protected HoldButton nextButton;
    protected HoldButton volumeDownButton;
    protected ToggleButton muteToggleButton;
    protected HoldButton volumeUpButton;
    protected HoldButton repeatButton;
    protected HoldButton shuffleButton;

    Panel(PanelDevice panelDevice) {
        super(TITLE);
        this.panelDevice = panelDevice;
        classLoader = getClass().getClassLoader();
        createControls();
        layoutControls();
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    protected URL getResource(String name) {
        return classLoader.getResource(name);
    }

    protected ImageIcon getImageIcon(String name) {
        return new ImageIcon(getResource(name));
    }

    protected HoldButton getButton(String name, String text) {
        HoldButton button = new HoldButton(this);
        button.setIcon(getImageIcon(name));
        button.setToolTipText(text);
        button.setFocusPainted(false);
        return button;
    }

    protected ToggleButton getToggleButton(String firstName, String secondName, String text) {
        ImageIcon firstImageIcon = getImageIcon(firstName);
        ImageIcon secondImageIcon = getImageIcon(secondName);
        ToggleButton button = new ToggleButton(this, firstImageIcon, secondImageIcon);
        button.setToolTipText(text);
        button.setFocusPainted(false);
        return button;
    }

    protected void createControls() {
        upButton = getButton("icons/up.png", "Go to previous application");
        nextButton = getButton("icons/next.png", "Go to next track");
        previousButton = getButton("icons/previous.png", "Go to previous track");
        rewindButton = getButton("icons/rewind.png", "Skip backward");
        playPauseToggleButton = getToggleButton("icons/play.png", "icons/pause.png", "Play/pause");
        forwardButton = getButton("icons/forward.png", "Skip forward");
        downButton = getButton("icons/down.png", "Go to next application");
        volumeDownButton = getButton("icons/volumeDown.png", "Decrease volume");
        muteToggleButton = getToggleButton("icons/mute.png", "icons/unmute.png", "Toggle Mute");
        volumeUpButton = getButton("icons/volumeUp.png", "Increase volume");
        repeatButton = getButton("icons/repeat.png", "Repeat");
        shuffleButton = getButton("icons/shuffle.png", "Shuffle");
    }

    protected void layoutControls() {
        setLayout(new BorderLayout());
        layoutControlPanel();
    }
    
    protected void layoutControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        
        JPanel upperControlPanel = new JPanel();
        controlPanel.add(upperControlPanel, BorderLayout.NORTH);
        upperControlPanel.add(upButton);
        upperControlPanel.add(previousButton);
        upperControlPanel.add(rewindButton);
        upperControlPanel.add(playPauseToggleButton);
        upperControlPanel.add(forwardButton);
        upperControlPanel.add(nextButton);
        
        JPanel lowerControlPanel = new JPanel();
        controlPanel.add(lowerControlPanel, BorderLayout.SOUTH);
        lowerControlPanel.add(downButton);
        lowerControlPanel.add(repeatButton);
        lowerControlPanel.add(volumeDownButton);
        lowerControlPanel.add(muteToggleButton);
        lowerControlPanel.add(volumeUpButton);
        lowerControlPanel.add(shuffleButton);

        add(controlPanel, BorderLayout.CENTER);
    }

    /* Listeners */
    public void buttonPressed(HoldButton button) {
        if (button.equals(previousButton)) {
            panelDevice.buttonPressed(PanelButton.PREVIOUS);
        } else if (button.equals(rewindButton)) {
            panelDevice.buttonPressed(PanelButton.REWIND);
        } else if (button.equals(playPauseToggleButton)) {
            panelDevice.buttonPressed(PanelButton.PLAY);
        } else if (button.equals(forwardButton)) {
            panelDevice.buttonPressed(PanelButton.FORWARD);
        } else if (button.equals(nextButton)) {
            panelDevice.buttonPressed(PanelButton.NEXT);
        } else if (button.equals(volumeDownButton)) {
            panelDevice.buttonPressed(PanelButton.VOLUME_DOWN);
        } else if (button.equals(muteToggleButton)) {
            panelDevice.buttonPressed(PanelButton.MUTE);
        } else if (button.equals(volumeUpButton)) {
            panelDevice.buttonPressed(PanelButton.VOLUME_UP);
        } else if (button.equals(repeatButton)) {
            panelDevice.buttonPressed(PanelButton.REPEAT);
        } else if (button.equals(shuffleButton)) {
            panelDevice.buttonPressed(PanelButton.SHUFFLE);
        } else if (button.equals(upButton)) {
            panelDevice.buttonPressed(PanelButton.UP);
        }  else if (button.equals(downButton)) {
            panelDevice.buttonPressed(PanelButton.DOWN);
        }
    }

    public void buttonReleased(HoldButton button) {
        if (button.equals(previousButton)) {
            panelDevice.buttonReleased(PanelButton.PREVIOUS);
        } else if (button.equals(rewindButton)) {
            panelDevice.buttonReleased(PanelButton.REWIND);
        } else if (button.equals(playPauseToggleButton)) {
            panelDevice.buttonReleased(PanelButton.PLAY);
            playPauseToggleButton.toggle();
        } else if (button.equals(forwardButton)) {
            panelDevice.buttonReleased(PanelButton.FORWARD);
        } else if (button.equals(nextButton)) {
            panelDevice.buttonReleased(PanelButton.NEXT);
        } else if (button.equals(volumeDownButton)) {
            panelDevice.buttonReleased(PanelButton.VOLUME_DOWN);
        } else if (button.equals(muteToggleButton)) {
            panelDevice.buttonReleased(PanelButton.MUTE);
            muteToggleButton.toggle();
        } else if (button.equals(volumeUpButton)) {
            panelDevice.buttonReleased(PanelButton.VOLUME_UP);
        } else if (button.equals(repeatButton)) {
            panelDevice.buttonReleased(PanelButton.REPEAT);
        } else if (button.equals(shuffleButton)) {
            panelDevice.buttonReleased(PanelButton.SHUFFLE);
        } else if (button.equals(upButton)) {
            panelDevice.buttonReleased(PanelButton.UP);
        }  else if (button.equals(downButton)) {
            panelDevice.buttonReleased(PanelButton.DOWN);
        }
    }

    protected void processWindowEvent(WindowEvent event) {
        if (event.getID() == WindowEvent.WINDOW_CLOSING) {
            log.debug("Window closing");
            try {
                panelDevice.deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        }
    }
}
