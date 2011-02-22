package pm.device.text.lan;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import pm.device.text.InputListener;

public class SocketListener implements Runnable {
    protected boolean run;

    protected ServerSocket server;
    protected ArrayList<InputListener> inputListenerList;

    public SocketListener(ServerSocket server) {
        this.server = server;
        inputListenerList = new ArrayList<InputListener>();
    }

    public void start() {
        new Thread(this).start();
    }

    public void run() {
        run = true;
        while (run) {
            try {
                Socket socket = server.accept();
                InputStream inputStream = socket.getInputStream();
                InputListener inputListener = new InputListener(inputStream);
                inputListenerList.add(inputListener);
                inputListener.start();
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
    }

    public void stop() {
        run = false;
        for (InputListener inputListener : inputListenerList) {
            inputListener.stop();
        }
    }
}
