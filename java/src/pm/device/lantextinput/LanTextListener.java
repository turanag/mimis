package pm.device.lantextinput;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import pm.device.textinput.TextDevice;

public class LanTextListener extends TextDevice {
    protected Socket socket;
    
    public LanTextListener(Socket socket) {
        this.socket = socket;
        initialise();
    }
    
    public void initialise() {
        try {
            input = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        run = true;
        new Thread(this).start();
    }
       
    protected boolean running() {
        return run && socket.isConnected() && input.hasNext();
    }
    
    public void exit() {
        run = false;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
