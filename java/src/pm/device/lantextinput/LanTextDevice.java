package pm.device.lantextinput;

import java.io.IOException;
import java.net.ServerSocket;
import pm.Device;

public class LanTextDevice extends Device implements Runnable {
    static final int PORT = 1234;
    
    protected ServerSocket socket;

    public void initialise() {
        try {
            socket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    public void run() {
        while(true){
            try {
                new LanTextListener(socket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
