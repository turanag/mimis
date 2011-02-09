package pm.application.iTunes;

import pm.application.Application;

import com.dt.iTunesController.ITCOMDisabledReason;
import com.dt.iTunesController.ITTrack;
import com.dt.iTunesController.iTunes;
import com.dt.iTunesController.iTunesEventsInterface;

public class iTunesApplication extends Application implements iTunesEventsInterface {
    protected iTunes iTunes;
    protected boolean connected;

    public iTunesApplication() {
        iTunes = new iTunes();
        connected = false;
    }

    public void start() {
        if (!connected) {
            iTunes.connect();
            iTunes.addEventHandler(this);
            connected = true;
        }
    }

    public void exit() {
        if (connected) {
            iTunes.quit();
        }
    }

    /* Actions */
    public void play() {
        if (connected) {
            iTunes.playPause();
        }
    }

    public void pause() {
        if (connected) {
            iTunes.playPause();
        }
    }

    public void resume() {
        if (connected) {
            iTunes.resume();
        }
    }

    /* iTunesEventInterface => naar eigen class? */
    @Override
    public void onDatabaseChangedEvent(int[][] deletedObjectIDs,
            int[][] changedObjectIDs) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayerPlayEvent(ITTrack iTrack) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayerStopEvent(ITTrack iTrack) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayerPlayingTrackChangedEvent(ITTrack iTrack) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onCOMCallsDisabledEvent(ITCOMDisabledReason reason) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onCOMCallsEnabledEvent() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onQuittingEvent() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onAboutToPromptUserToQuitEvent() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onSoundVolumeChangedEvent(int newVolume) {
        // TODO Auto-generated method stub
        
    }
}