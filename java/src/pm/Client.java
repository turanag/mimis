package pm;

import pm.event.spreader.NetworkSpreader;
import pm.exception.device.DeviceInitialiseException;
import pm.exception.event.spreader.NetworkSpreaderException;

public class Client extends Manager {
    public static final String IP = "192.168.1.100";
    public static final int PORT = 6789;

    public Client(String ip, int port) throws NetworkSpreaderException {
        super(new NetworkSpreader(ip, port));
    }

    public Client() throws NetworkSpreaderException {
        this(IP, PORT);
    }

    public void start() {
        log.info("LocalManager!");
        try {
            initialise();
        } catch (DeviceInitialiseException e) {}
        super.start(false);
    }

    public static void main(String[] args) {
        try {
            new Client().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
