package pm.application.iTunes;

import pm.application.Application;

import com.dt.iTunesController.ITCOMDisabledReason;
import com.dt.iTunesController.ITTrack;
import com.dt.iTunesController.iTunes;
import com.dt.iTunesController.iTunesEventsInterface;

public class iTunesApplication extends Application implements iTunesEventsInterface {
    
    protected final int VOLUME_CHANGE_RATE = 5;
    protected final int SEEK_TIME = 1000;
    
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

    public void next() {
        if (connected) {
            iTunes.nextTrack();
        }
    }
    
    public void previous() {
        if (connected) {
            iTunes.previousTrack();
        }
    }
    
    public void forward() {
        if (connected) {
            iTunes.fastForward();
            //sleep(SEEK_TIME);
            resume();
        }
    }
    
    public void rewind() {
        if (connected) {
            iTunes.rewind();
            //sleep(SEEK_TIME);
            resume();
        }
    }
    
    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void mute() {
        if (connected) {
            iTunes.setMute(iTunes.getMute());
        }
    }
    
    protected int volume() {
        if (connected) {
            return iTunes.getSoundVolume();
        } else {
            return 0;
        }
    }
    
    public void volumeUp() {
        if (connected) {
            iTunes.setSoundVolume(volume() + VOLUME_CHANGE_RATE);
        }
    }
    
    public void volumeDown() {
        if (connected) {
            iTunes.setSoundVolume(volume() - VOLUME_CHANGE_RATE);
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