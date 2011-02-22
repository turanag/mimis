package pm.application.windows.wmp;

import pm.Action;
import pm.application.windows.Command;
import pm.application.windows.WindowsApplication;
import pm.exception.application.ApplicationInitialiseException;
import pm.exception.application.windows.SendCommandException;

public class WMPApplication extends WindowsApplication {
    protected final static String PROGRAM = "wmplayer.exe";
    protected final static String TITLE = "Windows Media Player";
    protected final static String NAME = "WMPlayerApp";

    public WMPApplication() {
        super(PROGRAM, TITLE, NAME);
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
