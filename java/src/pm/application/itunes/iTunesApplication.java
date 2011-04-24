package pm.application.itunes;

import pm.Application;
import pm.exception.application.ApplicationExitException;
import pm.value.Action;

import com.dt.iTunesController.ITCOMDisabledReason;
import com.dt.iTunesController.ITTrack;
import com.dt.iTunesController.iTunes;
import com.dt.iTunesController.iTunesEventsInterface;

public class iTunesApplication extends Application implements iTunesEventsInterface {
    protected static final int POSTION_CHANGE_RATE = 1;
    protected static final int VOLUME_CHANGE_RATE = 5;
    protected static final String PLAYLIST_LIKE = "Like";
    protected static final String PLAYLIST_DISLIKE = "Dislike";

    protected iTunes iTunes;

    public iTunesApplication() {
        super();
        iTunes = new iTunes();
    }

    public void initialise() {
        iTunes.connect();
        iTunes.addEventHandler(this);
    }

    public void exit() throws ApplicationExitException {
        System.out.println("Exit iTunesApplication");
        super.exit();
        try {
            iTunes.quit(); // Todo: wachten totdat ook daadwerkelijk gestart? Anders wordt iTunes niet afgesloten.
        } catch (Exception e) {
            throw new ApplicationExitException();
        }
    }
 
    protected void action(Action action) {
        System.out.println("iTunesApplication: " + action);
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
                iTunes.setPlayerPosition(iTunes.getPlayerPosition() + POSTION_CHANGE_RATE);
                break;
            case REWIND:
                iTunes.setPlayerPosition(iTunes.getPlayerPosition() - POSTION_CHANGE_RATE);
                break;
            case MUTE:
                iTunes.toggleMute();
                break;
            case VOLUME_UP:
                iTunes.setSoundVolume(getVolume() + VOLUME_CHANGE_RATE);
                break;
            case VOLUME_DOWN:
                iTunes.setSoundVolume(getVolume() - VOLUME_CHANGE_RATE);
                break;
            case SHUFFLE:
                iTunes.toggleShuffle();
                //iTunes.fastForward();
                break;
            case REPEAT:
                iTunes.cycleSongRepeat();
                //iTunes.resume();
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

    /* iTunesEventInterface => naar eigen class? */
    public void onDatabaseChangedEvent(int[][] deletedObjectIDs, int[][] changedObjectIDs) {}
    public void onPlayerPlayEvent(ITTrack iTrack) {}
    public void onPlayerStopEvent(ITTrack iTrack) {}
    public void onPlayerPlayingTrackChangedEvent(ITTrack iTrack) {}
    public void onCOMCallsDisabledEvent(ITCOMDisabledReason reason) {}
    public void onCOMCallsEnabledEvent() {}
    public void onQuittingEvent() {}
    public void onAboutToPromptUserToQuitEvent() {}
    public void onSoundVolumeChangedEvent(int newVolume) {}
}