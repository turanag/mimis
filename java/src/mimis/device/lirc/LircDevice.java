package mimis.device.lirc;

import mimis.Button;
import mimis.device.lirc.button.ColorButton;
import mimis.device.lirc.button.NumberButton;
import mimis.device.lirc.remote.DenonRC176Button;
import mimis.device.lirc.remote.PhiliphsRCLE011Button;
import mimis.device.lirc.remote.SamsungBN5901015AButton;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.input.state.Press;
import mimis.input.state.Release;
import mimis.parser.ParserInput;
import mimis.util.Multiplexer;
import mimis.util.Native;
import mimis.util.multiplexer.SignalListener;
import mimis.value.Action;
import mimis.value.Signal;
import mimis.worker.Component;

public class LircDevice extends Component implements LircButtonListener, SignalListener<Button> {
    protected static final String TITLE = "Lirc";
    protected final static String PROGRAM = "winlirc.exe";

    protected Multiplexer<Button> multiplexer;
    protected LircService lircService;
    protected LircTaskMapCycle taskMapCycle;

    public LircDevice() {
        super(TITLE);
        multiplexer = new Multiplexer<Button>(this);
        lircService = new LircService();
        lircService.put(PhiliphsRCLE011Button.NAME, PhiliphsRCLE011Button.values());
        lircService.put(DenonRC176Button.NAME, DenonRC176Button.values());
        lircService.put(SamsungBN5901015AButton.NAME, SamsungBN5901015AButton.values());
        lircService.add(this);
        taskMapCycle = new LircTaskMapCycle();
    }

    protected void activate() throws ActivateException {
        lircService.start();
        route(new ParserInput(Action.ADD, taskMapCycle.denonRC176));
        route(new ParserInput(Action.ADD, taskMapCycle.philiphsRCLE011));
        route(new ParserInput(Action.ADD, taskMapCycle.samsungBN5901015A));
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

    public void add(Signal signal, Button button) {
        add(signal, button, true);
    }

    public void add(Signal signal, Button button, boolean general) {
        switch (signal) {
            case BEGIN:
                route(new Press(button));
                break;
            case END:
                route(new Release(button));
                break;
        }

        if (general) {
            String string = button.toString();
            for (Button colorButton : ColorButton.values()) {
                if (colorButton.toString().equals(string)) {
                    add(signal, ColorButton.valueOf(string), false);
                }
            }
            for (Button numberButton : NumberButton.values()) {
                if (numberButton.toString().equals(string)) {
                    add(signal, NumberButton.valueOf(string), false);
                }
            }
        }
    }    
}
