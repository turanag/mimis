package pm.application.windows.gomplayer;

import pm.Action;
import pm.application.windows.Command;
import pm.application.windows.VirtualKey;
import pm.application.windows.WindowsApplication;
import pm.exception.application.ApplicationInitialiseException;
import pm.exception.application.windows.SendCommandException;
import pm.exception.application.windows.SendKeyException;

public class GomPlayerApplication extends WindowsApplication {
    protected final static String PATH = "C:\\Program Files (x86)\\GRETECH\\GomPlayer\\";
    protected final static String PROGRAM = "GOM.exe";
    protected final static String NAME = "GOM Player";

    protected boolean playing;

    public GomPlayerApplication() {
        super(PATH, PROGRAM, NAME);
        playing = false;
    }

    public void initialise() {
        try {
            super.initialise();
        } catch (ApplicationInitialiseException e) {
            e.printStackTrace();
        }
    }

    public void action(Action action) {
        System.out.println("GomPlayerApplication: " + action);
        //http://www.keyxl.com/aaa0602/267/GOM-Player-keyboard-shortcuts.htm
        try {
            switch (action) {
                case PLAY:
                    key(VirtualKey.SPACE);
                    break;
               case NEXT:
                    command(Command.MEDIA_NEXTTRACK);
                    break;
                case PREVIOUS:
                    command(Command.MEDIA_PREVIOUSTRACK);
                    break;
                case FORWARD:
                    command(Command.MEDIA_FAST_FORWARD);
                    break;
                case REWIND:
                    command(Command.MEDIA_REWIND);
                    break;
                case MUTE:
                    key('m');
                    break;
                case VOLUME_UP:
                    key(VirtualKey.UP);
                    break;
                case VOLUME_DOWN:
                    key(VirtualKey.DOWN);
                    break;
                case SHUFFLE:
                    //
                    break;
            }
        } catch (SendCommandException e) {} catch (SendKeyException e) {}
    }
}
