package pm;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
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
    protected String[] applicationNames = {"GOM Player", "Windows Media Player", "iTunes", "Media Player Classic", "VLC", "Winamp"};

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
                Application application = null;
                applicationButtons[i] = new ApplicationButton(application);
                applicationButtons[i].setText(applicationName);
                Class.forName(applicationName).newInstance();

                System.out.println("App added");
            } catch (ClassNotFoundException e) {e.printStackTrace();
            } catch (InstantiationException e) {e.printStackTrace();
            } catch (IllegalAccessException e) {e.printStackTrace();}
        }
    }

    protected void layoutButtons() {
        JPanel applicationPanel = new JPanel(new GridLayout(0, 1));
        for (int i = 0; i < applicationButtons.length; i++) {
            System.out.println(applicationButtons[i]);
            applicationPanel.add(applicationButtons[i]);            
        }
        add(applicationPanel);
    }
}
