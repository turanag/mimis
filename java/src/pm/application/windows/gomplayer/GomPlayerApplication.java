package pm.application.windows.gomplayer;

import pm.Action;
import pm.application.windows.Command;
import pm.application.windows.Type;
import pm.application.windows.Key;
import pm.application.windows.WindowsApplication;
import pm.exception.application.windows.SendCommandException;
import pm.exception.application.windows.SendKeyException;

public class GomPlayerApplication extends WindowsApplication {
    protected final static String REGISTRY = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Clients\\Media\\GomPlayer\\shell\\open\\command";
    protected final static String PROGRAM = "GOM.exe";
    protected final static String NAME = "GOM Player";

    public GomPlayerApplication() {
        super(PROGRAM, NAME, "C:\\Program Files (x86)\\GomPlayer\\GOM.exe");
    }

    public void action(Action action) {
        System.out.println("GomPlayerApplication: " + action);
        //http://www.keyxl.com/aaa0602/267/GOM-Player-keyboard-shortcuts.htm
        try {
            switch (action) {
                case PLAY:
                    key(Type.DOWN, Key.SPACE);
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
                    key(Type.DOWN, 'm');
                    break;
                case VOLUME_UP:
                    key(Type.DOWN, Key.UP);
                    break;
                case VOLUME_DOWN:
                    key(Type.DOWN, Key.DOWN);
                    break;
            }
        } catch (SendCommandException e) {} catch (SendKeyException e) {}
    }
}
