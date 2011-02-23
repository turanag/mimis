package pm.application.windows.gomplayer;

import pm.application.windows.WindowsApplication;
import pm.exception.application.windows.SendCommandException;
import pm.exception.application.windows.SendKeyException;
import pm.value.Action;
import pm.value.Command;
import pm.value.Key;
import pm.value.Type;

public class GomPlayerApplication extends WindowsApplication {
    protected final static String PROGRAM = "GOM.exe";
    protected final static String TITLE = "GOM Player";
    protected final static String NAME = "GomPlayer1.x";

    public GomPlayerApplication() {
        super(PROGRAM, TITLE, NAME);
    }

    public void action(Action action) {
        System.out.println(handle);
        System.out.println("GomPlayerApplication: " + action);
        try {
            switch (action) {
                case PLAY:
                    command(0x800C);
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
