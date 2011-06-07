package mimis.device.lirc;

import java.util.HashMap;

import mimis.Button;
import mimis.Device;
import mimis.device.lirc.button.DenonRC176;
import mimis.device.lirc.button.PhiliphsRCLE011Button;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.sequence.state.Press;
import mimis.sequence.state.Release;
import mimis.util.Multiplexer;
import mimis.util.multiplexer.SignalListener;
import mimis.value.Signal;

public class LircDevice extends Device implements LircButtonListener, SignalListener {
    protected static final String TITLE = "Lirc";

    protected Multiplexer multiplexer;
    protected LircService lircService;

    public LircDevice() {
        super(TITLE);

        HashMap<String, LircButton[]> buttonMap = new HashMap<String, LircButton[]>();
        buttonMap.put(PhiliphsRCLE011Button.NAME, PhiliphsRCLE011Button.values());
        buttonMap.put(DenonRC176.NAME, DenonRC176.values());

        multiplexer = new Multiplexer(this);

        lircService = new LircService(buttonMap);
        lircService.add(this);
    }

    public void activate() throws ActivateException {
        multiplexer.start();
        lircService.activate();
        super.activate();
    }

    public void deactivate() throws DeactivateException {
        multiplexer.deactivate();
        lircService.deactivate();
        super.deactivate();
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

    public void stop() {
        multiplexer.stop();
        lircService.stop();
        super.stop();
    }
}
