package mimis.application.cmd.windows.wmp;

import mimis.Worker;
import mimis.application.cmd.windows.WindowsApplication;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.value.Action;

public class WMPApplication extends WindowsApplication {
    protected final static String PROGRAM = "wmplayer.exe";
    protected final static String TITLE = "Windows Media Player";
    protected final static String WINDOW = "WMPlayerApp";

    protected static final int VOLUME_SLEEP = 120;

    protected VolumeWorker volumeWorker;
    
    public WMPApplication() {
        super(PROGRAM, TITLE, WINDOW);
        volumeWorker = new VolumeWorker();
    }

    public void begin(Action action) {
        log.trace("WMPApplication begin: " + action);
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
                command(18812);
                break;
            case MUTE:
                command(18817);
                break;
            case VOLUME_UP:
                try {
                    volumeWorker.activate(1);
                } catch (ActivateException e) {
                    log.error(e);
                }
                break;
            case VOLUME_DOWN:
                try {
                    volumeWorker.activate(-1);
                } catch (ActivateException e) {
                    log.error(e);
                }
                break;
            case SHUFFLE:
                command(18842);
                break;
            case REPEAT:
                command(18843);
                break;
        }
    }
    
    public void end(Action action) {
        log.trace("WMPApplication end: " + action);
        switch (action) {
            case FORWARD:
                command(18813);
                break;
            case REWIND:
                command(18812);
                break;
            case VOLUME_UP:
            case VOLUME_DOWN:
                try {
                    volumeWorker.deactivate();
                } catch (DeactivateException e) {
                    log.error(e);
                }
                break;
        }
    }
    
    protected class VolumeWorker extends Worker {
        protected int volumeChangeSign;

        public void activate(int volumeChangeSign) throws ActivateException {
            super.activate();
            this.volumeChangeSign = volumeChangeSign;
        }

        public void work() {
            command (volumeChangeSign > 0 ? 18815 : 18816);
            sleep(VOLUME_SLEEP);
        }
    };
}
