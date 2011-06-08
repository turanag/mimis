package mimis.application.cmd.windows.gomplayer;

import mimis.application.cmd.windows.WindowsApplication;
import mimis.value.Action;

public class GomPlayerApplication extends WindowsApplication {
    protected final static String PROGRAM = "GOM.exe";
    protected final static String TITLE = "GOM Player";
    protected final static String NAME = "GomPlayer1.x";

    public GomPlayerApplication() {
        super(PROGRAM, TITLE, NAME);
    }

    public void begin(Action action) {
        log.trace("GomPlayerApplication begin: " + action);
        switch (action) {
            case PLAY:
                command(0x800C);
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
}
