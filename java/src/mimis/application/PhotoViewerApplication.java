package mimis.application;

import mimis.Worker;
import mimis.application.robot.RobotApplication;
import mimis.exception.worker.DeactivateException;
import mimis.value.Action;
import mimis.value.Key;

public class PhotoViewerApplication extends RobotApplication {
    protected final static String TITLE = "Photo Viewer";

    protected static final int ZOOM_SLEEP = 100;
    protected static final int DELETE_SLEEP = 2000;

    protected ZoomWorker zoomWorker;
    protected boolean fullscreen;

    public PhotoViewerApplication() {
        super(TITLE);
        zoomWorker = new ZoomWorker();
        fullscreen = false;
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        zoomWorker.stop();
    }

    public void exit() {
        super.exit();
        zoomWorker.exit();
    }

    public void begin(Action action) {
        switch (action) {
            case VOLUME_UP:
                zoomWorker.start(1);    
                break;
            case VOLUME_DOWN:
                zoomWorker.start(-1);
                break;
        }
    }

    public void end(Action action) {
        log.trace("PhotoViewerApplication end: " + action);
        switch (action) {
            case VOLUME_UP:
            case VOLUME_DOWN:
                zoomWorker.stop();
                break;
            case FORWARD:
                break;
            case REWIND:
                break;
            case NEXT:
                press(Key.RIGHT);
                break;
            case PREVIOUS:
                press(Key.LEFT);
                break;
            case MUTE:
                press(Key.CONTROL);
                press(Key.NUMPAD0);
                release(Key.CONTROL);
                break;
            case FULLSCREEN:
                press(fullscreen ? Key.ESCAPE : Key.F11);
                fullscreen = !fullscreen;
                break;
            case DISLIKE:
                boolean restore = false;
                if (fullscreen) {
                    end(Action.FULLSCREEN);
                    sleep(DELETE_SLEEP);
                    restore = true;
                }
                press(Key.F16);
                press('Y');
                if (restore) {
                    sleep(DELETE_SLEEP);
                    end(Action.FULLSCREEN);
                }
                break;
        }
    }

    protected class ZoomWorker extends Worker {
        protected int zoomDirection;

        public void start(int zoomDirection) {
            super.start();
            this.zoomDirection = zoomDirection;
        }

        public void work() {
            Key key = zoomDirection > 0 ? Key.ADD : Key.SUBTRACT;
            press(key);
            sleep(ZOOM_SLEEP);
        }
    }
}
