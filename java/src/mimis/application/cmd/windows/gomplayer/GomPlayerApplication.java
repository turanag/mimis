package mimis.application.cmd.windows.gomplayer;

import mimis.Worker;
import mimis.application.cmd.windows.WindowsApplication;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.value.Action;
import mimis.value.Amount;

public class GomPlayerApplication extends WindowsApplication {
    protected final static String PROGRAM = "GOM.exe";
    protected final static String TITLE = "GOM Player";
    protected final static String NAME = "GomPlayer1.x";

    protected static final int VOLUME_SLEEP = 100;
    protected static final int SEEK_SLEEP = 100;

    protected VolumeWorker volumeWorker;
    protected SeekWorker seekWorker;

    public GomPlayerApplication() {
        super(PROGRAM, TITLE, NAME);
        volumeWorker = new VolumeWorker();
        seekWorker = new SeekWorker();
    }
    
    public void stop() {
        super.stop();
        volumeWorker.stop();
        seekWorker.stop();
    }

    public void begin(Action action) {
        log.trace("GomPlayerApplication begin: " + action);
        try {
            switch (action) {
                case VOLUME_UP:                    
                    volumeWorker.activate(1);    
                    break;
                case VOLUME_DOWN:
                    volumeWorker.activate(-1);
                    break;
                case FORWARD:
                    seekWorker.activate(Amount.SMALL, 1);
                    break;
                case REWIND:
                    seekWorker.activate(Amount.SMALL, -1);
                    break;
                case NEXT:
                    seekWorker.activate(Amount.MEDIUM, 1);
                    break;
                case PREVIOUS:
                    seekWorker.activate(Amount.MEDIUM, -1);
                    break;  
            }
        } catch (ActivateException e) {
            log.error(e);
        }
    }

    public void end(Action action) {
        log.trace("GomPlayerApplication end: " + action);
        switch (action) {
            case PLAY:
                command(0x800C);
                break;
            case MUTE:
                command(0x8016);
                break;
            case FORWARD:
            case REWIND:
            case NEXT:
            case PREVIOUS:
                try {
                    seekWorker.deactivate();
                } catch (DeactivateException e) {
                    log.error(e);
                }
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
                command(0x8154);
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
            command(volumeChangeSign > 0 ? 0x8014 : 0x8013);
            sleep(VOLUME_SLEEP);
        }
    };

    protected class SeekWorker extends Worker {
        protected Amount amount;
        protected int seekDirection;

        public void activate(Amount amount, int seekDirection) throws ActivateException {
            super.activate();
            this.amount = amount;
            this.seekDirection = seekDirection;
        }

        public void work() {
            switch (amount) {
                case SMALL:
                    command(seekDirection > 0 ? 0x8009 : 0x8008);
                    break;
                case MEDIUM:
                    command(seekDirection > 0 ? 0x800B : 0x800A);
                    break;
                case LARGE:
                    command(seekDirection > 0 ? 0x8012 : 0x8011);
                    break;
            }
            sleep(SEEK_SLEEP);
        }
    };
}
