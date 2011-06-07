package mimis.device.wiimote;

import java.io.IOException;
import java.util.Scanner;

import mimis.Worker;

public class WiimoteDiscovery extends Worker {
    protected WiimoteDevice wiimoteDevice;

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
            Process process = Runtime.getRuntime().exec(command);
            Scanner scanner = new Scanner(process.getInputStream());
            while (scanner.hasNext()) {
                if (scanner.next().equals("[OK]")) {
                    return true;
                }
            }
        } catch (IOException e) {
            log.error(e);
        }
        return false;
    }

    protected void work() {
        log.debug("Discover wiimotes");
        if (connect()) {
            wiimoteDevice.connected();
        }
    }

    public void stop() {
        super.stop();
        disconnect();
    }
}
