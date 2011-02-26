package pm.application.vlc;

import pm.Application;
import pm.application.windows.WindowsApplication;
import pm.value.Action;

public class VLCApplication extends WindowsApplication {
    protected final static String PROGRAM = "vlc.exe";
    protected final static String TITLE = "VLC media player";
    protected final static String NAME = "CabinetWClass";
    
    public VLCApplication() {
        super(PROGRAM, TITLE, NAME);
    }
    
    public void action(Action action) {
        System.out.println("VLCApplication: " + action);
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
                command(18814);
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
