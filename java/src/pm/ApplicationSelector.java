package pm;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.util.ArrayCycle;

public class ApplicationSelector extends JFrame {
    protected static final long serialVersionUID = 1L;
    protected final static String TITLE = "MIMIS Application Selector";
    
    protected ArrayCycle<Application> applicationCycle;
    
    protected JToggleButton gomPlayer;
    protected JToggleButton windowsMediaPlayer;
    protected JToggleButton iTunes;
    protected JToggleButton mediaPlayerClassic;
    protected JToggleButton vlc;
    protected JToggleButton winamp;
       
    protected JToggleButton[] applicationButtons = {gomPlayer, windowsMediaPlayer, iTunes, mediaPlayerClassic, vlc, winamp};
    protected String[] applicationNames = {"GOM Player", "Windows Media Player", "iTunes", "Media Player Classic", "VLC", "Winamp"};;

    public ApplicationSelector(ArrayCycle<Application> applicationCycle) {
        super(TITLE);
        System.out.println("Application Selector started");
        this.applicationCycle = applicationCycle;
        createButtons();
        layoutButtons();
        pack();
        setResizable(false);
        setVisible(true);
    }

    protected void createButtons() {
        for (int i = 0; i < applicationButtons.length; i++) {
            try {
                String applicationName = applicationNames[i];
                applicationButtons[i] = new JToggleButton(applicationName);
                Application application = (Application) Class.forName(applicationName).newInstance();
                ToggleChangeListener toggleChangeListener = new ToggleChangeListener(application);
                applicationButtons[i].addChangeListener(toggleChangeListener);
                System.out.println("App added");
            } catch (ClassNotFoundException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {}
        }
    }
    
    protected void layoutButtons() {
        JPanel applicationPanel = new JPanel(new GridLayout(0, 1));
        for (int i = 0; i < applicationButtons.length; i++) {
            applicationPanel.add(applicationButtons[i]);
        }
        add(applicationPanel);
    }

    protected class ToggleChangeListener implements ChangeListener {
        Application application;
        
        public ToggleChangeListener(Application application) {
            this.application = application;
        }
        
        public void stateChanged(ChangeEvent changeEvent) {
            System.out.println("Event!");
            AbstractButton abstractButton = (AbstractButton) changeEvent.getSource();
            ButtonModel buttonModel = abstractButton.getModel();
            boolean armed = buttonModel.isArmed();
            boolean pressed = buttonModel.isPressed();
            boolean selected = buttonModel.isSelected();
            System.out.println("Changed: " + armed + "/" + pressed + "/" + selected);
                /*try {
                    application.initialise();
                    application.start();
                    applicationCycle.add(application);
                } catch (ApplicationInitialiseException e) {}*/
                    /*applicationCycle.remove(application);
                    application.exit();*/
        }
    }

}
