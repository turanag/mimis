package pm.application.windows.wmp;

import java.util.prefs.Preferences;

import com.eaio.nativecall.IntCall;

import pm.Action;
import pm.application.windows.Command;
import pm.application.windows.WindowsApplication;
import pm.exception.application.ApplicationInitialiseException;
import pm.exception.application.windows.SendCommandException;

public class WMPApplication extends WindowsApplication {
    protected final static String PATH = "C:\\Program Files (x86)\\Windows Media Player\\";
    protected final static String PROGRAM = "wmplayer.exe";
    protected final static String NAME = "Windows Media Player";

    public WMPApplication() {
        super(PATH, PROGRAM, NAME);
    }

    public void initialise() throws ApplicationInitialiseException {
        super.initialise();
        //RegistryKey;
    }

    public void action(Action action) {
        System.out.println("WMPApplication: " + action);
        try {
            switch (action) {
                case PLAY:
                    command(Command.MEDIA_PLAY_PAUSE);
                    break;
                case TEST:
                    command(Command.VOLUME_MUTE);
                    break;
            }
        } catch (SendCommandException e) {}
    }
}
