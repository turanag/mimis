package pm.device.panel;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pm.util.swing.HoldButton;
import pm.util.swing.HoldButtonListener;
import pm.util.swing.ToggleButton;

public class Panel extends JFrame implements HoldButtonListener {
    protected static final long serialVersionUID = 1L;
    protected final static String TITLE = "MIMIS Panel Device";

    protected PanelButtonListener panelButtonListener;
    protected ClassLoader classLoader;

    //protected JTextArea feedbackArea;
    //protected JScrollPane scrollPane;

    protected HoldButton previousButton;
    protected HoldButton rewindButton;
    protected HoldButton stopButton;
    protected ToggleButton playPauseToggleButton;
    protected HoldButton forwardButton;
    protected HoldButton nextButton;
    protected HoldButton volumeDownButton;
    protected ToggleButton muteToggleButton;
    protected HoldButton volumeUpButton;
    protected HoldButton repeatButton;
    protected HoldButton shuffleButton;

    Panel(PanelButtonListener panelButtonListener) {
        super(TITLE);
        this.panelButtonListener = panelButtonListener;
        classLoader = getClass().getClassLoader();
        createControls();
        layoutControls();
        pack();
        setResizable(false);
        setVisible(true);
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
        previousButton = getButton("icons/previous.png", "Go to previous track");
        rewindButton = getButton("icons/rewind.png", "Skip backward");
        playPauseToggleButton = getToggleButton("icons/play.png", "icons/pause.png", "Play/pause");//getButton("icons/play.png", "Play/pause");
        forwardButton = getButton("icons/forward.png", "Skip forward");
        nextButton = getButton("icons/next.png", "Go to next track");
        volumeDownButton = getButton("icons/volumeDown.png", "Decrease volume");
        muteToggleButton = getToggleButton("icons/mute.png", "icons/unmute.png", "Toggle Mute");
        volumeUpButton = getButton("icons/volumeUp.png", "Increase volume");
        repeatButton = getButton("icons/repeat.png", "Repeat");
        shuffleButton = getButton("icons/shuffle.png", "Shuffle");
    }

    protected void layoutControls() {
        setLayout(new BorderLayout());
        //layoutFeedbackPanel();
        layoutControlPanel();
    }
    
    /*protected void layoutFeedbackPanel() {
        JPanel feedbackPanel = new JPanel();
        feedbackArea = new JTextArea(10, 32);
        feedbackArea.setEditable(false);
        feedbackPanel.add(feedbackArea);
        
        scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().add(feedbackArea);
        feedbackPanel.add(scrollPane);
       
        add(feedbackPanel, BorderLayout.SOUTH);
        
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");     
        feedbackArea.append("Hier komt allerlei feedback te staan!\n");  
        
        
        JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
    }*/

    protected void layoutControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        
        JPanel upperControlPanel = new JPanel();
        controlPanel.add(upperControlPanel, BorderLayout.NORTH);        
        upperControlPanel.add(previousButton);
        upperControlPanel.add(rewindButton);
        upperControlPanel.add(playPauseToggleButton);
        upperControlPanel.add(forwardButton);
        upperControlPanel.add(nextButton);
        
        JPanel lowerControlPanel = new JPanel();
        controlPanel.add(lowerControlPanel, BorderLayout.SOUTH);
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
            panelButtonListener.buttonPressed(PanelButton.PREVIOUS);
        } else if (button.equals(rewindButton)) {
            panelButtonListener.buttonPressed(PanelButton.REWIND);
        } else if (button.equals(playPauseToggleButton)) {
            panelButtonListener.buttonPressed(PanelButton.PLAY);
        } else if (button.equals(forwardButton)) {
            panelButtonListener.buttonPressed(PanelButton.FORWARD);
        } else if (button.equals(nextButton)) {
            panelButtonListener.buttonPressed(PanelButton.NEXT);
        } else if (button.equals(volumeDownButton)) {
            panelButtonListener.buttonPressed(PanelButton.VOLUME_DOWN);
        } else if (button.equals(muteToggleButton)) {
            panelButtonListener.buttonPressed(PanelButton.MUTE);
        } else if (button.equals(volumeUpButton)) {
            panelButtonListener.buttonPressed(PanelButton.VOLUME_UP);
        } else if (button.equals(repeatButton)) {
            panelButtonListener.buttonPressed(PanelButton.REPEAT);
        } else if (button.equals(shuffleButton)) {
            panelButtonListener.buttonPressed(PanelButton.SHUFFLE);
        }
    }

    public void buttonReleased(HoldButton button) {
        if (button.equals(previousButton)) {
            panelButtonListener.buttonReleased(PanelButton.PREVIOUS);
        } else if (button.equals(rewindButton)) {
            panelButtonListener.buttonReleased(PanelButton.REWIND);
        } else if (button.equals(playPauseToggleButton)) {
            panelButtonListener.buttonReleased(PanelButton.PLAY);
            playPauseToggleButton.toggle();
        } else if (button.equals(forwardButton)) {
            panelButtonListener.buttonReleased(PanelButton.FORWARD);
        } else if (button.equals(nextButton)) {
            panelButtonListener.buttonReleased(PanelButton.NEXT);
        } else if (button.equals(volumeDownButton)) {
            panelButtonListener.buttonReleased(PanelButton.VOLUME_DOWN);
        } else if (button.equals(muteToggleButton)) {
            panelButtonListener.buttonReleased(PanelButton.MUTE);
            muteToggleButton.toggle();
        } else if (button.equals(volumeUpButton)) {
            panelButtonListener.buttonReleased(PanelButton.VOLUME_UP);
        } else if (button.equals(repeatButton)) {
            panelButtonListener.buttonReleased(PanelButton.REPEAT);
        } else if (button.equals(shuffleButton)) {
            panelButtonListener.buttonReleased(PanelButton.SHUFFLE);
        }
    }

    /* Feedback */
    /*public void addFeedback(String format, Object... args) {
        feedbackArea.append(String.format(format, args));
        JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
    }

    public void clearFeedback() {
        feedbackArea.setText("");
    }*/
}
