package mimis.application.lirc;

import mimis.Application;
import mimis.device.lirc.LircButton;
import mimis.device.lirc.LircService;
import mimis.device.lirc.remote.WC02IPOButton;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;

public class LircApplication extends Application {
    protected LircService lircService;

    public LircApplication(String title) {
        super(title);
        lircService = new LircService();
        lircService.put(WC02IPOButton.NAME, WC02IPOButton.values());
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

    public void send(LircButton button) {
        lircService.send(button);
    }
}
