package pm.application.windows.wmp;

import pm.application.windows.WindowsApplication;
import pm.value.Action;
import pm.value.Command;

public class WMPApplication extends WindowsApplication {
    protected final static String PROGRAM = "wmplayer.exe";
    protected final static String TITLE = "Windows Media Player";
    protected final static String NAME = "WMPlayerApp";

    public WMPApplication() {
        super(PROGRAM, TITLE, NAME);
    }

    public void action(Action action) {
        System.out.println("WMPApplication: " + action);
        System.out.println(handle);
        switch (action) {
            case PLAY:
                command(18808);
                break;
            case NEXT:
                command(18811);
                break;
            case PREVIOUS:
                command(18810);
                break;
            case FORWARD:
                command(18813);
                break;
            case REWIND:
                command(18812);
                break;
            case MUTE:
                command(18817);
                break;
            case VOLUME_UP:
                command(18815);
                break;
            case VOLUME_DOWN:
                command(18816);
                break;
            case SHUFFLE:
                command(18842);
                break;
            case REPEAT:
                command(18843);
                break;
        }
    }
}
