package mimis.application.itunes;

import java.io.IOException;

import mimis.Application;
import mimis.Worker;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.util.VBScript;
import mimis.value.Action;

import com.dt.iTunesController.ITCOMDisabledReason;
import com.dt.iTunesController.ITTrack;
import com.dt.iTunesController.iTunes;
import com.dt.iTunesController.iTunesEventsInterface;

public class iTunesApplication extends Application implements iTunesEventsInterface {
    protected static final String TITLE = "iTunes";
    protected static final String PROGRAM = "iTunes.exe";
    protected static final boolean QUIT = true;

    protected static final int VOLUME_CHANGE_RATE = 5;
    protected static final int VOLUME_SLEEP = 100;
    protected static final String PLAYLIST_LIKE = "Like";
    protected static final String PLAYLIST_DISLIKE = "Dislike";

    protected iTunes iTunes;
    protected VolumeWorker volumeWorker;
    protected boolean quiting;

    public iTunesApplication() {
        super(TITLE);        
        iTunes = new iTunes();
        volumeWorker = new VolumeWorker();
        quiting = false;
    }

    public void activate() throws ActivateException {
        synchronized (iTunes) {
            iTunes.connect();
            iTunes.addEventHandler(this);
        }
        super.activate();
    }

    public boolean active() {
        try {
            if (!active && !quiting && VBScript.isRunning(PROGRAM)) {
                try {
                    activate();
                } catch (ActivateException e) {
                    log.error(e);
                }
            }
        } catch (IOException e) {
            log.error(e);
        }
        try {
            iTunes.getMute();
            active = true;
        } catch (Exception e) {
            active = false;
        }
        return active;
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        volumeWorker.deactivate();
        try {
            if (QUIT) {
                quiting = true;
                synchronized (iTunes) {
                    iTunes.quit();
                }
                quiting = false;
            }
        } catch (Exception e) {
            throw new DeactivateException();
        }
    }

    public void stop() throws DeactivateException {
        super.stop();
        volumeWorker.stop();
    }

    protected void begin(Action action) {
        log.trace("iTunesApplication begin: " + action);
        if (!active) return;
        switch (action) {
            case FORWARD:
                iTunes.fastForward();
                break;
            case REWIND:
                iTunes.rewind();
                break;
            case VOLUME_UP:
                try {
                    volumeWorker.activate(VOLUME_CHANGE_RATE);
                } catch (ActivateException e) {
                    log.error(e);
                }
                break;
            case VOLUME_DOWN:
                try {
                    volumeWorker.activate(-VOLUME_CHANGE_RATE);
                } catch (ActivateException e) {
                    log.error(e);
                }
                break;
        }
    }

    protected void end(Action action) {
        log.trace("iTunesApplication end: " + action);
        if (!active) return;
        switch (action) {
            case PLAY:
                iTunes.playPause();
                break;
            case NEXT:
                iTunes.nextTrack();
                break;
            case PREVIOUS:
                iTunes.previousTrack();
                break;
            case FORWARD:
                iTunes.resume();
                break;
            case REWIND:
                iTunes.resume();
                break;
            case MUTE:
                iTunes.toggleMute();
                break;
            case VOLUME_UP:
            case VOLUME_DOWN:
                try {
                    volumeWorker.deactivate();
                } catch (DeactivateException e) {
                    log.error(e);
                }
                break;
            case SHUFFLE:
                iTunes.toggleShuffle();
                break;
            case REPEAT:
                iTunes.cycleSongRepeat();
                break;
            case LIKE:
                iTunes.playlistAddCurrentTrack(PLAYLIST_LIKE);
                break;
            case DISLIKE:
                iTunes.playlistAddCurrentTrack(PLAYLIST_DISLIKE);
                break;
        }
    }

    protected int getVolume() {
        return iTunes.getSoundVolume();
    }

    public void onDatabaseChangedEvent(int[][] deletedObjectIDs, int[][] changedObjectIDs) {}
    public void onPlayerPlayEvent(ITTrack iTrack) {
        if (active) {
            log.trace("iTunesEvent: play");
        }
    }

    public void onPlayerStopEvent(ITTrack iTrack) {
        if (active) {
            log.trace("iTunesEvent: stop");
        }
    }

    public void onPlayerPlayingTrackChangedEvent(ITTrack iTrack) {}
    public void onCOMCallsDisabledEvent(ITCOMDisabledReason reason) {}
    public void onCOMCallsEnabledEvent() {}
    public void onQuittingEvent() {}
    public void onAboutToPromptUserToQuitEvent() {}
    public void onSoundVolumeChangedEvent(int newVolume) {}    

    protected class VolumeWorker extends Worker {
        protected int volumeChangeRate;

        public void activate(int volumeChangeRate) throws ActivateException {
            super.activate();
            this.volumeChangeRate = volumeChangeRate;
        }

        public void work() {
            iTunes.setSoundVolume(getVolume() + volumeChangeRate);
            sleep(VOLUME_SLEEP);
        }
    };
}