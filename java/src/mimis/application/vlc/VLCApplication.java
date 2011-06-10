package mimis.application.vlc;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mimis.application.cmd.CMDApplication;
import mimis.value.Action;

public class VLCApplication extends CMDApplication {
    protected final static String PROGRAM = "vlc.exe";
    protected final static String TITLE = "VLC media player";

    protected static final int POSTION_CHANGE_RATE = 1;
    protected static final int VOLUME_CHANGE_RATE = 20;

    protected static final String HOST = "127.0.0.1"; // localhost
    protected static final int PORT = 1234;

    protected int volume = 255;
    protected boolean muted = false;

    public VLCApplication() {
        super(PROGRAM, TITLE);
    }

    public void command(String command) {
        //String request = "http://" + HOST + ":" + PORT + "/requests/status.xml?command=" + command;
        String request = String.format("http://%s:%d/requests/status.xml?command=%s", HOST, PORT, command);
        try {
            // Todo: check response voor 200, status ok
            //int response = ((HttpURLConnection)(new URL(request)).openConnection()).getResponseCode();
            URL url = new URL(request);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            int response = httpUrlConnection.getResponseCode();
            log.debug("Response: " + response);
        } catch (MalformedURLException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public void end(Action action) {
        log.trace("VLCApplication end: " + action);
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
                command("volume&val=" + toggleMute());
                break;
            case VOLUME_UP:
                volumeUp();
                break;
            case VOLUME_DOWN:
                volumeDown();
                break;
            case SHUFFLE:
                command("command=pl_random");
                break;
            case REPEAT:
                command("command=pl_repeat");
                break;
        }
    }
    
    protected void volumeUp() {
        if (!muted) {
            volume += VOLUME_CHANGE_RATE;
            command("volume&val=+" + VOLUME_CHANGE_RATE);
        }
    }
    
    protected void volumeDown() {
        if (!muted) {
            volume -= VOLUME_CHANGE_RATE;
            command("volume&val=-" + VOLUME_CHANGE_RATE);
        }
    }

    protected int toggleMute() {
        return (muted = !muted) ? 0 : volume;
    }

    public String title() {
        return TITLE;
    }
}
