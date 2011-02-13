package pm.application.Winamp;

import pm.Action;
import pm.Application;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;

import com.qotsa.exception.InvalidHandle;
import com.qotsa.exception.InvalidParameter;
import com.qotsa.jni.controller.WinampController;

public class WinampApplication extends Application {
    protected boolean muted;
    protected int volume;

    public void initialise() throws ApplicationInitialiseException {
        try {
            WinampController.run();
            volume = 0; // UnsatisfiedLinkError: com.qotsa.jni.controller.JNIWinamp.getVolume()I
            muted = volume == 0;
        } catch (Exception e) {
            throw new ApplicationInitialiseException();
        }
    }

    public void exit() throws ApplicationExitException {
        System.out.println("Exit WinampApplication");
        super.exit();
        try {
            WinampController.exit(); // Todo: wachten totdat ook daadwerkelijk gestart? Anders crashed Winamp.
        } catch (InvalidHandle e) {
            throw new ApplicationExitException();
        }
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
                    toggleMute();
                    break;
                case VOLUME_UP:
                    WinampController.increaseVolume();
                    break;
                case VOLUME_DOWN:
                    WinampController.decreaseVolume();
                    break;
            }
        } catch (InvalidHandle e) {}
    }

    protected void toggleMute() throws InvalidHandle {
        if (!muted) {
            volume = WinampController.getVolume();
        }
        try {
            WinampController.setVolume(muted ? volume : 0);
        } catch (InvalidParameter e) {}
        muted = !muted;        
    }
}
