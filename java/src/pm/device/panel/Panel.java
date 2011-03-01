package pm.device.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pm.Device;
import pm.macro.state.Press;

public class Panel extends JFrame implements ChangeListener, HoldButtonListener {
	protected static final long serialVersionUID = 1L;
    protected final static String TITLE = "MIMIS Panel Device";

    protected PanelButtonListener panelButtonListener;
	protected ClassLoader classLoader;

	protected JLabel timeLabel;
	protected JLabel chapterLabel;

	protected JProgressBar positionProgressBar;	
	protected JSlider positionSlider;
	protected JSlider volumeSlider;

	protected JButton previousChapterButton;
	protected JButton rewindButton;
	protected JButton stopButton;
	protected JButton pauseButton;
	protected JButton playButton;
	protected JButton fastForwardButton;
	protected JButton nextChapterButton;
	protected JButton toggleMuteButton;
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
		timeLabel = new JLabel("hh:mm:ss");
		chapterLabel = new JLabel("00/00");

		positionProgressBar = new JProgressBar();
		positionProgressBar.setToolTipText("Time");

		positionSlider = new JSlider();
		positionSlider.setToolTipText("Position");

		volumeSlider = new JSlider();
		volumeSlider.setOrientation(JSlider.HORIZONTAL);
		volumeSlider.setPreferredSize(new Dimension(100, 40));
		volumeSlider.setToolTipText("Change volume");

		previousChapterButton = getButton("icons/control_start_blue.png", "Go to previous chapter");
		rewindButton = getButton("icons/control_rewind_blue.png", "Skip back");
		stopButton = getButton("icons/control_stop_blue.png", "Stop");
		pauseButton = getButton("icons/control_pause_blue.png", "Play/pause");
		playButton = getButton("icons/control_play_blue.png", "Play");
		fastForwardButton = getButton("icons/control_fastforward_blue.png", "Skip forward");
		nextChapterButton = getButton("icons/control_end_blue.png", "Go to next chapter");
		toggleMuteButton = getButton("icons/sound_mute.png", "Toggle Mute");
	}

	protected void layoutControls() {
		setLayout(new BorderLayout());
	
		JPanel positionPanel = new JPanel();
		positionPanel.setLayout(new GridLayout(2, 1));
		positionPanel.add(positionProgressBar);
		positionPanel.add(positionSlider);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(8, 0));

		topPanel.add(timeLabel, BorderLayout.WEST);
		topPanel.add(positionPanel, BorderLayout.CENTER);
		topPanel.add(chapterLabel, BorderLayout.EAST);
		add(topPanel, BorderLayout.NORTH);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());

		bottomPanel.add(previousChapterButton);
		bottomPanel.add(rewindButton);
		bottomPanel.add(stopButton);
		bottomPanel.add(pauseButton);
		bottomPanel.add(playButton);
		bottomPanel.add(fastForwardButton);
		bottomPanel.add(nextChapterButton);
		bottomPanel.add(volumeSlider);
		bottomPanel.add(toggleMuteButton);

		add(bottomPanel, BorderLayout.SOUTH);

		positionSlider.addChangeListener(this);
		volumeSlider.addChangeListener(this);
	}

	/* Listeners */
	public void stateChanged(ChangeEvent event) {
		Object source = event.getSource();
		if (positionSlider.equals(source)) {
			if (!setPositionValue) {
				float positionValue = (float) positionSlider.getValue() / 100.0f;
				System.out.println(positionValue);
			}
		} else if (volumeSlider.equals(source)) {
			
		}		
	}

	public void buttonPressed(HoldButton button) {
		if (button.equals(previousChapterButton)) {
		    panelButtonListener.buttonPressed(PanelButton.PREVIOUS_CHAPTER);
		} else if (button.equals(rewindButton)) {
		    panelButtonListener.buttonPressed(PanelButton.REWIND);
        } else if (button.equals(stopButton)) {
            panelButtonListener.buttonPressed(PanelButton.STOP);
        } else if (button.equals(pauseButton)) {
            panelButtonListener.buttonPressed(PanelButton.PAUSE);
        } else if (button.equals(playButton)) {
            panelButtonListener.buttonPressed(PanelButton.PLAY);
        } else if (button.equals(fastForwardButton)) {
            panelButtonListener.buttonPressed(PanelButton.FAST_FORWARD);
        } else if (button.equals(nextChapterButton)) {
            panelButtonListener.buttonPressed(PanelButton.NEXT_CHAPTER);
        } else if (button.equals(toggleMuteButton)) {
            panelButtonListener.buttonPressed(PanelButton.TOGGLE_MUTE);
        }
	}

	public void buttonReleased(HoldButton button) {
      if (button.equals(previousChapterButton)) {
            panelButtonListener.buttonReleased(PanelButton.PREVIOUS_CHAPTER);
        } else if (button.equals(rewindButton)) {
            panelButtonListener.buttonReleased(PanelButton.REWIND);
        } else if (button.equals(stopButton)) {
            panelButtonListener.buttonReleased(PanelButton.STOP);
        } else if (button.equals(pauseButton)) {
            panelButtonListener.buttonReleased(PanelButton.PAUSE);
        } else if (button.equals(playButton)) {
            panelButtonListener.buttonReleased(PanelButton.PLAY);
        } else if (button.equals(fastForwardButton)) {
            panelButtonListener.buttonReleased(PanelButton.FAST_FORWARD);
        } else if (button.equals(nextChapterButton)) {
            panelButtonListener.buttonReleased(PanelButton.NEXT_CHAPTER);
        } else if (button.equals(toggleMuteButton)) {
            panelButtonListener.buttonReleased(PanelButton.TOGGLE_MUTE);
        }
	}

	/* Update */
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
}
