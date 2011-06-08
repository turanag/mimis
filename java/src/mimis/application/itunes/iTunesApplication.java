package mimis.application.itunes;

import mimis.Application;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.value.Action;

import com.dt.iTunesController.ITCOMDisabledReason;
import com.dt.iTunesController.ITTrack;
import com.dt.iTunesController.iTunes;
import com.dt.iTunesController.iTunesEventsInterface;

public class iTunesApplication extends Application implements iTunesEventsInterface {
    protected static final String TITLE = "iTunes";

    protected static final boolean QUIT = false;
    protected static final int VOLUME_CHANGE_RATE = 5;
    protected static final int VOLUME_SLEEP = 500;
    protected static final String PLAYLIST_LIKE = "Like";
    protected static final String PLAYLIST_DISLIKE = "Dislike";

    protected iTunes iTunes;
    protected boolean volume;

    public iTunesApplication() {
        super(TITLE);        
        iTunes = new iTunes();        
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
            iTunes.getMute();
            active = true;
        } catch (Exception e) {
            active = false;
        }
        return active;
    }

    public void deactivate() throws DeactivateException {
        try {
            if (QUIT) {
                synchronized (iTunes) {
                    iTunes.quit();
                }
            }
        } catch (Exception e) {
            throw new DeactivateException();
        } finally {
            super.deactivate();
        }
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
                volume(true);
                break;
            case VOLUME_DOWN:
                volume(false);
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
                volume = false;
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

    protected void volume(boolean up) {
        volume = true;
        while (volume) {
            int change = (up ? 1 : -1) * VOLUME_CHANGE_RATE; 
            iTunes.setSoundVolume(getVolume() + change);
            sleep(VOLUME_SLEEP);
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
}