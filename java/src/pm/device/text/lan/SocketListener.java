package pm.device.text.lan;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import pm.Worker;
import pm.device.text.TextDevice;

public class SocketListener extends Worker {
    protected ServerSocket server;
    protected ArrayList<TextDevice.InputListener> inputListenerList;

    public SocketListener(ServerSocket server) {
        this.server = server;
        inputListenerList = new ArrayList<TextDevice.InputListener>();
    }

    public void run() {
        while (run) {
            try {
                Socket socket = server.accept();
                InputStream inputStream = socket.getInputStream();
                TextDevice.InputListener inputListener = new TextDevice().new InputListener(inputStream);
                inputListenerList.add(inputListener);
                inputListener.start();
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
    }

    public void stop() {
        run = false;
        for (TextDevice.InputListener inputListener : inputListenerList) {
            inputListener.stop();
        }
    }
}
