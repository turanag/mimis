package pm.application.windows.winamp;

import pm.application.cmd.windows.WindowsApplication;
import pm.value.Action;

public class WinampApplication extends WindowsApplication {
    protected final static String PROGRAM = "winamp.exe";
    protected final static String TITLE = "Winamp";
    protected final static String NAME = "Winamp v1.x";

    protected final static int STATUS_PLAYING = 1;
    protected final static int STATUS_PAUSED = 3;
    protected final static int STATUS_STOPPED = 0;
    
    protected final static int IPC_ISPLAYING = 104;
    protected final static int IPC_GETOUTPUTTIME = 105;
    protected final static int IPC_SETVOLUME = 122;

    protected final static int WINAMP_FILE_QUIT     = 40001;
    protected final static int WINAMP_FILE_REPEAT   = 40022;
    protected final static int WINAMP_FILE_SHUFFLE  = 40023;
    protected final static int WINAMP_BUTTON1       = 40044;
    protected final static int WINAMP_BUTTON2       = 40045;
    protected final static int WINAMP_BUTTON3       = 40046;
    protected final static int WINAMP_BUTTON5       = 40048;   
    protected final static int WINAMP_VOLUMEUP      = 40058;
    protected final static int WINAMP_VOLUMEDOWN    = 40059;
    protected final static int WINAMP_FFWD5S        = 40060;
    protected final static int WINAMP_REW5S         = 40061;
    protected final static int WINAMP_BUTTON4_SHIFT = 40147;
    protected final static int WINAMP_VISPLUGIN     = 40192;

    protected double volume;
    protected boolean muted;

    public WinampApplication() {
        super(PROGRAM, TITLE, NAME);
        volume = getVolume();
        muted = volume == 0;
    }

    public void action(Action action) {
        System.out.println("WinampApplication: " + action);
        switch (action) {
            case PLAY:
                System.out.println(user(0, IPC_ISPLAYING));
                switch (user(0, IPC_ISPLAYING)) {
                    case STATUS_STOPPED:
                        command(WINAMP_BUTTON2);
                        break;
                    default:
                        command(WINAMP_BUTTON3);
                        break;
                }                
                break;
            case NEXT:
                command(WINAMP_BUTTON5);
                break;
            case PREVIOUS:
                command(WINAMP_BUTTON1);
                break;
            case FORWARD:
                command(WINAMP_FFWD5S);
                break;
            case REWIND:
                command(WINAMP_REW5S);
                break;
            case MUTE:
                System.out.println(getDuration() +" "+ getElapsed());
                if (muted) {
                    setVolume(volume);
                } else {
                    volume = getVolume();
                    setVolume(0);
                }
                muted = !muted;
                break;
            case VOLUME_UP:
                System.out.println(getVolume());
                command(WINAMP_VOLUMEUP);
                break;
            case VOLUME_DOWN:
                System.out.println(getVolume());
                command(WINAMP_VOLUMEDOWN);
                break;
            case SHUFFLE:
                command(WINAMP_FILE_SHUFFLE);
                break;
            case REPEAT:
                command(WINAMP_FILE_REPEAT);
                break;
            case FADEOUT:
                command(WINAMP_BUTTON4_SHIFT);
                break;
            case QUIT:
                command(WINAMP_FILE_QUIT);
                break;
            case VISUALISER:
                command(WINAMP_VISPLUGIN);
                break;
        }
    }

    public double getVolume() {
        return user(-666, IPC_SETVOLUME) / 255f;
    }

    public void setVolume(double volume) {
        user((int) Math.ceil(volume * 255), IPC_SETVOLUME);
    }

    public int getDuration() {
        return user(1, IPC_GETOUTPUTTIME);
    }

    public int getElapsed() {
        return user(0, IPC_GETOUTPUTTIME) / 1000;
    }
}

