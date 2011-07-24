package mimis.application.irtoy.ipod;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;

import mimis.Application;
import mimis.Worker;
import mimis.application.irtoy.Command;
import mimis.application.irtoy.Config;
import mimis.application.irtoy.Remote;
import mimis.device.lirc.LircService;
import mimis.device.lirc.remote.WC02IPOButton;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.value.Action;

public class iPodApplication extends Application {
    public static void main(String[] args) {
        Config config = Config.getInstance();
        Remote remote = config.getRemote("WC02-IPO");
        if (remote != null) {
            Command command = remote.getCommand("PLAY");
            if (command != null) {
                try {
                    File file = File.createTempFile("irtoy", ".bin"); 
                    file.deleteOnExit();
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    bufferedWriter.write(command.getData());
                    bufferedWriter.close();
                    String cmd = String.format("irtoy -d COM3 -f %s -p -a 0", file.getAbsoluteFile());
                    System.out.println(cmd);
                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    process.getOutputStream().write(byteArrayOutputStream.toByteArray());
                    System.out.println(byteArrayOutputStream.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected static final String TITLE = "iPod";
    protected static final boolean QUIT = true;

    protected static final int VOLUME_SLEEP = 100;

    protected LircService lircService;
    protected VolumeWorker volumeWorker;

    public iPodApplication() {
        super(TITLE);
        lircService = new LircService();
        lircService.put(WC02IPOButton.NAME, WC02IPOButton.values());
        volumeWorker = new VolumeWorker();
    }

    public void activate() throws ActivateException {
        lircService.activate();
        super.activate();
    }

    public boolean active() {
        return active = lircService.active();
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        lircService.deactivate();
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
                lircService.send(WC02IPOButton.PLAY);
                break;
            case NEXT:
                lircService.send(WC02IPOButton.NEXT);
                break;
            case PREVIOUS:
                lircService.send(WC02IPOButton.PREVIOUS);
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
            lircService.send(volumeChangeRate > 0 ? WC02IPOButton.PLUS : WC02IPOButton.MINUS);
        }

        public void work() {
            lircService.send(WC02IPOButton.HOLD);
            sleep(VOLUME_SLEEP);
        }
    };
}