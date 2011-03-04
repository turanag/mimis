package pm.device.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Panel extends JFrame implements ChangeListener, HoldButtonListener {
	protected static final long serialVersionUID = 1L;
    protected final static String TITLE = "MIMIS Panel Device";

    protected PanelButtonListener panelButtonListener;
	protected ClassLoader classLoader;

	protected JButton previousButton;
	protected JButton rewindButton;
	protected JButton stopButton;
	protected JButton pauseButton;
	protected JButton playButton;
	protected JButton forwardButton;
	protected JButton nextButton;
	protected JButton volumeDownButton;
	protected JButton muteButton;
	protected JButton volumeUpButton;
	protected boolean setPositionValue;

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

	protected JButton getButton(String name, String text) {
		JButton button = (JButton) new HoldButton(this);
		button.setIcon(getImageIcon(name));
		button.setToolTipText(text);
		return button;
	}

	protected void createControls() {
		previousButton = getButton("icons/previous.png", "Go to previous track");
		rewindButton = getButton("icons/rewind.png", "Skip backward");
		stopButton = getButton("icons/stop.png", "Stop");
		pauseButton = getButton("icons/pause.png", "Play/pause");
		playButton = getButton("icons/play.png", "Play/pause");
		forwardButton = getButton("icons/forward.png", "Skip forward");
		nextButton = getButton("icons/next.png", "Go to next track");
        volumeDownButton = getButton("icons/volumeDown.png", "Decrease volume");
		muteButton = getButton("icons/mute.png", "Toggle Mute");
	    volumeUpButton = getButton("icons/volumeUp.png", "Increase volume");
	}

	protected void layoutControls() {
		//setLayout(new BorderLayout());
	    
	
		//JPanel positionPanel = new JPanel();
	    
	    /* Feedback panel */
	    JPanel feedbackPanel = new JPanel();
	    JTextField feedbackField = new JTextField("Hier komt allerlei feedback te staan!", 64);
	    feedbackField.setEditable(false);
	    feedbackPanel.add(feedbackField);
	    
	    add(feedbackPanel, BorderLayout.NORTH);
	    
	    /* Control panel */
		JPanel controlPanel = new JPanel();

		controlPanel.add(previousButton);
		controlPanel.add(rewindButton);
		controlPanel.add(stopButton);
		controlPanel.add(pauseButton);
		controlPanel.add(playButton);
		controlPanel.add(forwardButton);
		controlPanel.add(nextButton);
		controlPanel.add(volumeDownButton);
		controlPanel.add(muteButton);
		controlPanel.add(volumeUpButton);

		add(controlPanel, BorderLayout.CENTER);
		/* TODO: zorg dat de knoppen 4x3 of 3x4 staan, voeg shuffle en repeat toe
        JPanel controlPanel = new JPanel();
        
        JPanel seekPanel = new JPanel();            
        seekPanel.add(previousButton);
        seekPanel.add(rewindButton);
        seekPanel.add(forwardButton);
        seekPanel.add(nextButton);
        
        JPanel modePanel = new JPanel();
        controlPanel.add(stopButton);
        controlPanel.add(pauseButton);
        controlPanel.add(playButton);
        
        JPanel volumePanel = new JPanel();
        controlPanel.add(volumeDownButton);
        controlPanel.add(muteButton);
        controlPanel.add(volumeUpButton);

        controlPanel.add(seekPanel);
        controlPanel.add(modePanel);
        controlPanel.add(volumePanel);
        
        add(controlPanel, BorderLayout.CENTER);
        */
	}

	/* Listeners */
	public void stateChanged(ChangeEvent event) {
		/* Wordt niet meer gebruikt 
		Object source = event.getSource();
		if (positionSlider.equals(source)) {
			if (!setPositionValue) {
				float positionValue = (float) positionSlider.getValue() / 100.0f;
				System.out.println(positionValue);
			}
		} else if (volumeSlider.equals(source)) {
			
		}
		*/
	}

	public void buttonPressed(HoldButton button) {
		if (button.equals(previousButton)) {
		    panelButtonListener.buttonPressed(PanelButton.PREVIOUS);
		} else if (button.equals(rewindButton)) {
		    panelButtonListener.buttonPressed(PanelButton.REWIND);
        } else if (button.equals(stopButton)) {
            panelButtonListener.buttonPressed(PanelButton.STOP);
        } else if (button.equals(pauseButton)) {
            panelButtonListener.buttonPressed(PanelButton.PAUSE);
        } else if (button.equals(playButton)) {
            panelButtonListener.buttonPressed(PanelButton.PLAY);
        } else if (button.equals(forwardButton)) {
            panelButtonListener.buttonPressed(PanelButton.FORWARD);
        } else if (button.equals(nextButton)) {
            panelButtonListener.buttonPressed(PanelButton.NEXT);
        } else if (button.equals(volumeDownButton)) {
            panelButtonListener.buttonPressed(PanelButton.VOLUME_DOWN);
        } else if (button.equals(muteButton)) {
            panelButtonListener.buttonPressed(PanelButton.MUTE);
        } else if (button.equals(volumeUpButton)) {
            panelButtonListener.buttonPressed(PanelButton.VOLUME_UP);
        }
	}

	public void buttonReleased(HoldButton button) {
      if (button.equals(previousButton)) {
            panelButtonListener.buttonReleased(PanelButton.PREVIOUS);
        } else if (button.equals(rewindButton)) {
            panelButtonListener.buttonReleased(PanelButton.REWIND);
        } else if (button.equals(stopButton)) {
            panelButtonListener.buttonReleased(PanelButton.STOP);
        } else if (button.equals(pauseButton)) {
            panelButtonListener.buttonReleased(PanelButton.PAUSE);
        } else if (button.equals(playButton)) {
            panelButtonListener.buttonReleased(PanelButton.PLAY);
        } else if (button.equals(forwardButton)) {
            panelButtonListener.buttonReleased(PanelButton.FORWARD);
        } else if (button.equals(nextButton)) {
            panelButtonListener.buttonReleased(PanelButton.NEXT);
        } else if  (button.equals(volumeDownButton)) {
            panelButtonListener.buttonReleased(PanelButton.VOLUME_DOWN);
        } else if (button.equals(muteButton)) {
            panelButtonListener.buttonReleased(PanelButton.MUTE);
        } else if (button.equals(volumeUpButton)) {
            panelButtonListener.buttonReleased(PanelButton.VOLUME_UP);
        }
	}

	/* Update, is niet meer nodig? Alle informatie wordt in het TextField geprint
	public void updateTime(long millis) {
		String s = String.format(
			"%02d:%02d:%02d",
			TimeUnit.MILLISECONDS.toHours(millis),
			TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
			TimeUnit.MILLISECONDS.toSeconds(millis)	- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		timeLabel.setText(s);
	}

	public void updatePosition(int value) {
		positionProgressBar.setValue(value);
		setPositionValue = true;
		positionSlider.setValue(value);
		setPositionValue = false;
	}

	public void updateChapter(int chapter, int chapterCount) {
		String s = chapterCount != -1 ? (chapter + 1) + "/" + chapterCount
				: "-";
		chapterLabel.setText(s);
		chapterLabel.invalidate();
		validate();
	}

	public void updateVolume(int value) {
		volumeSlider.setValue(value);
	}
	*/
}
