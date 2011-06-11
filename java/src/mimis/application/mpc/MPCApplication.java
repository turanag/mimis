package mimis.application.mpc;

import mimis.Worker;
import mimis.application.cmd.windows.WindowsApplication;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.value.Action;

public class MPCApplication extends WindowsApplication {
    protected final static String PROGRAM = "mpc-hc.exe";
    protected final static String TITLE = "Media Player Classic";
    protected final static String NAME = "MediaPlayerClassicW";
    
    protected static final int VOLUME_SLEEP = 50;
    protected static final int SEEK_SLEEP = 50;

    protected VolumeWorker volumeWorker;
    protected SeekWorker seekWorker;
    
    public MPCApplication() {
        super(PROGRAM, TITLE, NAME);
        volumeWorker = new VolumeWorker();
        seekWorker = new SeekWorker();
    }
    
    public void begin(Action action) {
        log.trace("MPCApplication: " + action);
        try {
            switch (action) {
               case FORWARD:
                    seekWorker.activate(1);
                    break;
                case REWIND:
                    seekWorker.activate(-1);
                    break;
                case VOLUME_UP:
                    volumeWorker.activate(1);
                    break;
                case VOLUME_DOWN:
                    volumeWorker.activate(-1);
                    break;
            }
        } catch (ActivateException e) {
            log.error(e);
        }
    }
    
    public void end(Action action) {
        log.trace("MPCApplication: " + action);
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
            case REWIND:
                try {
                    seekWorker.deactivate();
                } catch (DeactivateException e) {
                    log.error(e);
                }
                break;
            case MUTE:
                command(909);
                break;
            case VOLUME_UP:
            case VOLUME_DOWN:
                try {
                    volumeWorker.deactivate();
                } catch (DeactivateException e) {
                    log.error(e);
                }
                break;
            case FULLSCREEN:
                command(830);
                break;
        }
    }

    public String title() {
        return TITLE;
    }

    protected class VolumeWorker extends Worker {
        protected int volumeChangeSign;

        public void activate(int volumeChangeSign) throws ActivateException {
            super.activate();
            this.volumeChangeSign = volumeChangeSign;
        }

        public void work() {
            command(volumeChangeSign > 0 ? 907 : 908);
            sleep(VOLUME_SLEEP);
        }
    };

    protected class SeekWorker extends Worker {
        protected int seekDirection;

        public void activate(int seekDirection) throws ActivateException {
            super.activate();
            this.seekDirection = seekDirection;
        }

        public void work() {
            command(seekDirection > 0 ? 900 : 889);
            sleep(SEEK_SLEEP);
        }
    };    
}
