package pm.application.Winamp;

import pm.Action;
import pm.Application;
import pm.exception.ApplicationException;
import pm.exception.application.ApplicationStartException;

import com.qotsa.exception.InvalidHandle;
import com.qotsa.exception.InvalidParameter;
import com.qotsa.jni.controller.WinampController;

public class WinampApplication extends Application {
    protected boolean muted;
    protected int volume;

    public void start() throws ApplicationException {
        try {
            WinampController.run();
            volume = WinampController.getVolume();
            muted = volume == 0;
        } catch (Exception e) {
            throw new ApplicationStartException();
        }
    }

    public void exit() {
        try {
            WinampController.exit();
        } catch (InvalidHandle e) {}
    }

    protected void action(Action action) {
        System.out.println("WinampApplication: " + action);
        try {
            switch (action) {
                case PLAY:
                    WinampController.play();
                case NEXT:
                    WinampController.nextTrack();
                    break;
                case PREVIOUS:
                    WinampController.previousTrack();
                    break;
                case FORWARD:
                    WinampController.fwd5Secs();
                    break;
                case REWIND:
                    WinampController.rew5Secs();
                    break;
                case MUTE:
                    if(muted) {
                        WinampController.setVolume(volume);
                    } else {
                        volume = WinampController.getVolume();
                        WinampController.setVolume(0);
                    }
                    muted = !muted;
                    break;
                case VOLUME_UP:
                    WinampController.increaseVolume();
                    break;
                case VOLUME_DOWN:
                    WinampController.decreaseVolume();
                    break;
            }
        } catch (InvalidHandle e) {
        } catch (InvalidParameter e) {}
    }
}
