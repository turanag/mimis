package pm.device.lirc;

import java.util.HashMap;

import pm.Device;
import pm.device.lirc.button.DenonRC176;
import pm.device.lirc.button.PhiliphsRCLE011Button;
import pm.util.Multiplexer;

public class LircDevice extends Device implements LircButtonListener {
    protected static final String TITLE = "Lirc";

    protected Multiplexer multiplexer;
    protected LircService lircService;

    public LircDevice() {
        super(TITLE);

        HashMap<String, LircButton[]> buttonMap = new HashMap<String, LircButton[]>();
        buttonMap.put(PhiliphsRCLE011Button.NAME, PhiliphsRCLE011Button.values());
        buttonMap.put(DenonRC176.NAME, DenonRC176.values());

        multiplexer = new Multiplexer();
        
        lircService = new LircService(buttonMap);
        lircService.add(this);
    }

    public void activate() {
        lircService.start();
        super.activate();
    }

    public void deactivate() {
        lircService.deactivate();
        super.deactivate();
    }

    public void add(LircButton lircButton) {
        
    }
}
