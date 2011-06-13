package mimis.device.wiimote;

import java.io.IOException;
import java.util.Scanner;

import mimis.Worker;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;

public class WiimoteDiscovery extends Worker {
    protected WiimoteDevice wiimoteDevice;
    protected Process process;
    protected boolean disconnect;

    public WiimoteDiscovery(WiimoteDevice wiimoteDevice) {
        this.wiimoteDevice = wiimoteDevice;
    }

    protected boolean connect() {
        return execute("-c nintendo");
    }

    protected boolean disconnect() {
        return execute("-d nintendo");
    }

    public boolean execute(String parameters) {
        String command = "native/wiiscan.exe -l none " + parameters;
        try {
            process = Runtime.getRuntime().exec(command);
            Scanner scanner = new Scanner(process.getInputStream());
            while (scanner.hasNext()) {
                if (scanner.next().equals("[OK]")) {
                    return true;
                }
            }
        } catch (IOException e) {
            log.error(e);
        } finally {
            process = null;
        }
        return false;
    }

    protected void work() {
        if (connect()) {
            wiimoteDevice.connected();
        } else if (disconnect) {
            disconnect();
            disconnect = false;
        }
    }

    public void activate() throws ActivateException {
        super.activate();
        disconnect = true;
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        if (process != null) {
            process.destroy();
        }
    }
}
