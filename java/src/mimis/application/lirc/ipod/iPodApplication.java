package mimis.application.lirc.ipod;

import mimis.Worker;
import mimis.application.lirc.LircApplication;
import mimis.device.lirc.remote.WC02IPOButton;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.value.Action;

public class iPodApplication extends LircApplication {
    protected static final String TITLE = "iPod";
    protected static final int VOLUME_SLEEP = 100;

    protected VolumeWorker volumeWorker;

    public iPodApplication() {
        super(TITLE);
        volumeWorker = new VolumeWorker();
    }

    public void stop() {
        super.stop();
        volumeWorker.stop();
    }

    protected void begin(Action action) {
        log.trace("iPodApplication begin: " + action);
        if (!active) return;
        switch (action) {
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
        }
    }

    protected void end(Action action) {
        log.trace("iPodApplication end: " + action);
        if (!active) return;
        switch (action) {
            case PLAY:
                send(WC02IPOButton.PLAY);
                break;
            case NEXT:
                send(WC02IPOButton.NEXT);
                break;
            case PREVIOUS:
                send(WC02IPOButton.PREVIOUS);
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
        protected int volumeChangeRate;

        public void activate(int volumeChangeRate) throws ActivateException {
            super.activate();
            this.volumeChangeRate = volumeChangeRate;
            send(volumeChangeRate > 0 ? WC02IPOButton.PLUS : WC02IPOButton.MINUS);
        }

        public void work() {
            lircService.send(WC02IPOButton.HOLD);
            sleep(VOLUME_SLEEP);
        }
    };
}