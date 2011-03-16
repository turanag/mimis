package pm.application.vlc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import pm.application.cmd.CMDApplication;
import pm.exception.application.ApplicationInitialiseException;
import pm.value.Action;

public class VLCApplication extends CMDApplication {
    protected final static String PROGRAM = "vlc.exe";
    protected final static String TITLE = "VLC media player";
    
    protected static final int POSTION_CHANGE_RATE = 1;
    protected static final int VOLUME_CHANGE_RATE = 20;

    protected static final String HOST = "127.0.0.1"; // localhost
    protected static final int PORT = 8080;
    
    public VLCApplication() {
        super(PROGRAM, TITLE);
    }

    public void initialise() throws ApplicationInitialiseException {
        super.initialise();
    }

    
    public void command(String command) {
        String url = "http://" + HOST + ":" + PORT;
        String request = "/requests/status.xml?command=" + command;
        System.out.println(url + request);
        try {
            new URL(url + request + "\r\n\n").openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void action(Action action) {
        System.out.println("VLCApplication: " + action);
        switch (action) {
            case PLAY:
                command("pl_pause");
                break;
            case PAUSE:
                command("pl_pause");
                break;
            case NEXT:
                command("pl_next");
                break;
            case PREVIOUS:
                command("pl_previous");
                break;
            case FORWARD:
                command("command=seek&val=+" + POSTION_CHANGE_RATE);
                break;
            case REWIND:
                command("command=seek&val=-" + POSTION_CHANGE_RATE);
                break;
            case MUTE:
                /*
                 * Nog implementeren
                 * command=volume&val=
                 */
                break;
            case VOLUME_UP:
                command("volume&val=+" + VOLUME_CHANGE_RATE);
                break;
            case VOLUME_DOWN:
                command("volume&val=-" + VOLUME_CHANGE_RATE);
                break;
            case SHUFFLE:
                command("command=pl_random");
                break;
            case REPEAT:
                command("command=pl_repeat");
                break;
        }
    }
}
