package pm.application.Winamp;

import pm.application.Application;

import com.qotsa.exception.InvalidHandle;
import com.qotsa.exception.InvalidParameter;
import com.qotsa.jni.controller.WinampController;

public class WinampApplication extends Application {

    protected boolean connected;
    
    protected boolean muted;
    protected int volume;

    public WinampApplication() {
        connected = false;
    }
    
    public void start() throws Exception {
        if (!connected) {
            WinampController.run();
            connected = true;
        }
    }

    public void exit() throws InvalidHandle {
        if (connected) {
            WinampController.exit();
            connected = false;
        }
    }

    /* Actions */
    public void play() throws InvalidHandle {
        if (connected) {
            WinampController.play();
        }
    }

    public void pause() throws InvalidHandle {
        if (connected) {
            WinampController.pause();
        }
    }

    public void resume() throws InvalidHandle {
        if (connected) {
            WinampController.resume();
        }
    }

    public void next() throws InvalidHandle {
        if (connected) {
            WinampController.nextTrack();
        }
    }
    
    public void previous() throws InvalidHandle {
        if (connected) {
            WinampController.previousTrack();
        }
    }
    
    public void forward() throws InvalidHandle {
        if (connected) {
            WinampController.fwd5Secs();
        }
    }
    
    public void rewind() throws InvalidHandle {
        if (connected) {
            WinampController.rew5Secs();
        }
    }
    
    
    public void mute() throws InvalidHandle, InvalidParameter {
        if (connected) {
            if(muted) {
                WinampController.setVolume(volume);
                muted = false;
            } else {
                volume = WinampController.getVolume();
                WinampController.setVolume(0);
                muted = true;
            }
        }
    }
    
    public void volumeUp() throws InvalidHandle {
        if (connected) {
            WinampController.increaseVolume();
        }
    }
    
    public void volumeDown() throws InvalidHandle {
        if (connected) {
            WinampController.decreaseVolume();
        }
    }
}
