package pm.application.windows.winamp;

import pm.application.cmd.windows.WindowsApplication;
import pm.util.Windows;
import pm.value.Action;

public class WinampApplication extends WindowsApplication {
    protected final static String PROGRAM = "winamp.exe";
    protected final static String TITLE = "Winamp";
    protected final static String NAME = "Winamp v1.x";

    public WinampApplication() {
        super(PROGRAM, TITLE, NAME);
    }

    public void action(Action action) {
        System.out.println("WinampApplication: " + action);
        switch (action) {
            case PLAY:
                command(40046);
                break;
            case FORWARD:
                command(0x8009);
                break;
            case REWIND:
                command(0x8008);
                break;
            case MUTE:
                command(0x8016);
                break;
            case VOLUME_UP:
                command(0x8014);
                break;
            case VOLUME_DOWN:
                command(0x8013);
                break;
        }
    }

    protected boolean playing() {
        int status = Windows.sendMessage(handle, Windows.WM_USER, 0, 104);
        return status == 1;
        // 1='playing', 3='paused' or 0='stopped'
    }
}

