package pm.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import pm.Device;
import pm.exception.device.DeviceInitialiseException;
import pm.value.Action;

public class NetworkServer extends Device {
    public static final int PORT = 1234; 
    protected MessageReceiver messageReceiver;
    
    public NetworkServer() {
        messageReceiver = new MessageReceiver(PORT);
        messageReceiver.start();
        System.out.println("NetworkServer started");
    }

    public void action(Action action) {
        this.action(action);
    }

    protected class MessageReceiver extends Thread {
        protected ServerSocket server;

        public MessageReceiver(int port) {
            try {
                server = new ServerSocket(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("MessageReceiver started");
        }

        public void run() {
            while (true) {
                System.out.println("MessageReceiver is waiting for clients");
                try {
                    Socket socket = server.accept();
                    final InputStream inputStream = socket.getInputStream();
                    new Thread() {
                        public void run(){
                            Scanner input = new Scanner(inputStream);
                            while (input.hasNext()) {
                                String string = input.next().toUpperCase();
                                if(string != null) {
                                    try {
                                       Action action = Action.deserialize(string);
                                       action(action);
                                    } catch(IllegalArgumentException e) {}
                                }
                                try {
                                    Thread.sleep(SLEEP);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } 
                        }
                    }.start();
                    System.out.println("Client connected");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
