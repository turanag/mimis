package pm.device.lirc;

import pm.Device;

public class LircDevice extends Device {
    protected static final String TITLE = "Lirc";

    protected LircService lircService;

    public LircDevice(String title) {
        super(title);
        lircService = new LircService();
    }

    public void activate() {
        lircService.activate();
        super.activate();
    }

    public void deactivate() {
        lircService.deactivate();
        super.deactivate();
    }
}
