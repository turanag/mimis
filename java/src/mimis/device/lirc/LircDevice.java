package mimis.device.lirc;

import mimis.Button;
import mimis.Device;
import mimis.device.lirc.remote.DenonRC176Button;
import mimis.device.lirc.remote.PhiliphsRCLE011Button;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.sequence.state.Press;
import mimis.sequence.state.Release;
import mimis.util.Multiplexer;
import mimis.util.Native;
import mimis.util.multiplexer.SignalListener;
import mimis.value.Signal;

public class LircDevice extends Device implements LircButtonListener, SignalListener {
    protected static final String TITLE = "Lirc";
    protected final static String PROGRAM = "winlirc.exe";

    protected Multiplexer multiplexer;
    protected LircService lircService;
    protected LircEventMapCycle eventMapCycle;

    public LircDevice() {
        super(TITLE);
        multiplexer = new Multiplexer(this);
        lircService = new LircService();
        lircService.put(PhiliphsRCLE011Button.NAME, PhiliphsRCLE011Button.values());
        lircService.put(DenonRC176Button.NAME, DenonRC176Button.values());
        lircService.put(DenonRC176Button.NAME, DenonRC176Button.values());
        lircService.add(this);
        eventMapCycle = new LircEventMapCycle();
    }

    protected void activate() throws ActivateException {
        lircService.start();
        add(eventMapCycle.denonRC176);
        add(eventMapCycle.philiphsRCLE011);
        add(eventMapCycle.samsungBN5901015A);
        super.activate();
    }

    public boolean active() {
        if (active && !lircService.active()) {
            stop();
        } else if (!active) {
            if (Native.isRunning(PROGRAM)) {
                start();
            }
        }
        return active;
    }

    protected void deactivate() throws DeactivateException {
        log.debug("Deactivate LircDevice");
        super.deactivate();
        lircService.stop();
        multiplexer.stop();
    }

    public void exit() {
        log.debug("Exit LircDevice");
        super.exit();
        lircService.exit();
        multiplexer.exit();
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
}
