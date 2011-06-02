package mimis.application.mpc;

import mimis.application.cmd.windows.WindowsApplication;
import mimis.value.Action;

public class MPCApplication extends WindowsApplication {
    protected final static String PROGRAM = "mpc-hc.exe";
    protected final static String TITLE = "Media Player Classic";
    protected final static String NAME = "MediaPlayerClassicW";
    
    public MPCApplication() {
        super(PROGRAM, TITLE, NAME);
    }
    
    public void action(Action action) {
        System.out.println("MPCApplication: " + action);
        System.out.println(handle);
        switch (action) {
            case PLAY:
                command(889);
                break;
            case NEXT:
                command(921);
                break;
            case PREVIOUS:
                command(920);
                break;
            case FORWARD:
                command(900);
                break;
            case REWIND:
                command(889);
                break;
            case MUTE:
                command(909);
                break;
            case VOLUME_UP:
                command(907);
                break;
            case VOLUME_DOWN:
                command(908);
                break;
            case FULLSCREEN:
                command(830);
                break;
        }
    }

    public String title() {
        return TITLE;
    }

}
