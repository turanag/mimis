package pm.device.text.lan;

import java.io.IOException;
import java.net.ServerSocket;
import pm.Device;
import pm.exception.device.DeviceInitialiseException;

public class LanTextDevice extends Device {
    static final int PORT = 1234;

    protected ServerSocket server;
    protected SocketListener socketListener;

    public void initialise() throws DeviceInitialiseException {
        try {
            server = new ServerSocket(PORT);
            socketListener = new SocketListener(server);
            socketListener.start();
        } catch (IOException e) {
            throw new DeviceInitialiseException();
        }
    }

    public void exit() {
        socketListener.stop();
    }
}
