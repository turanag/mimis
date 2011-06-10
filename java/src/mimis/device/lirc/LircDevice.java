package mimis.device.lirc;

import java.io.IOException;
import java.util.HashMap;

import mimis.Button;
import mimis.Device;
import mimis.device.lirc.remote.DenonRC176Button;
import mimis.device.lirc.remote.PhiliphsRCLE011Button;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.sequence.state.Press;
import mimis.sequence.state.Release;
import mimis.util.Multiplexer;
import mimis.util.multiplexer.SignalListener;
import mimis.util.VBScript;
import mimis.value.Signal;

public class LircDevice extends Device implements LircButtonListener, SignalListener {
    protected static final String TITLE = "Lirc";
    protected final static String PROGRAM = "winlirc.exe";

    protected Multiplexer multiplexer;
    protected LircService lircService;
    protected LircEventMapCycle eventMapCycle;

    public LircDevice() {
        super(TITLE);

        /* Initialise remotes */
        HashMap<String, LircButton[]> buttonMap = new HashMap<String, LircButton[]>();
        buttonMap.put(PhiliphsRCLE011Button.NAME, PhiliphsRCLE011Button.values());
        buttonMap.put(DenonRC176Button.NAME, DenonRC176Button.values());
        buttonMap.put(DenonRC176Button.NAME, DenonRC176Button.values());

        multiplexer = new Multiplexer(this);
        lircService = new LircService(buttonMap);
        lircService.add(this);

        eventMapCycle = new LircEventMapCycle();
    }

    public void activate() throws ActivateException {
        multiplexer.start();
        lircService.activate();
        add(eventMapCycle.denonRC176);
        add(eventMapCycle.philiphsRCLE011);
        add(eventMapCycle.samsungBN5901015A);
        super.activate();
    }

    public boolean active() {
        if (active && !lircService.active()) {
            active = false;
        } else if (!active) {
            try {
                if (VBScript.isRunning(PROGRAM)) {
                    try {
                        activate();
                    } catch (ActivateException e) {
                        log.error(e);
                    }
                }
            } catch (IOException e) {
                log.error(e);
            }
        }
        return active;
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        multiplexer.deactivate();
        lircService.deactivate();
    }

    public void add(LircButton lircButton) {
        multiplexer.add(lircButton);
    }

    public void add(Signal signal, Object object) {
        switch (signal) {
            case BEGIN:
                add(new Press((Button) object));
                break;
            case END:
                add(new Release((Button) object));
                break;
        }
    }

    public void stop() throws DeactivateException {
        multiplexer.stop();
        lircService.stop();
        super.stop();
    }
}
