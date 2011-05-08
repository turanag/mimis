package pm.device.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.Device;
import pm.Event;
import pm.Worker;
import pm.exception.device.DeviceInitialiseException;
import pm.value.Action;

public class NetworkDevice extends Device {
    public static final int PORT = 6789;

    protected Log log = LogFactory.getLog(NetworkDevice.class);
    protected int port;
    protected Server server;
    protected ArrayList<Client> clientList;

    public NetworkDevice(int port) {
        this.port = port;
    }

    public NetworkDevice() {
        this(PORT);
    }

    public void initialise() throws DeviceInitialiseException {
        try {
            server = new Server(port);
            server.start();
        } catch (IOException e) {
            throw new DeviceInitialiseException();
        }
    }

    public void exit() {
        server.stop();
    }

    protected void add(Action action) {

    }

    protected class Server extends Worker {
        protected ServerSocket serverSocket;

        public Server(int port) throws IOException {
            serverSocket = new ServerSocket(port);
            clientList = new ArrayList<Client>();
            System.out.println("Server started");
        }

        public void run() {
            while (run) {
                System.out.println("Server is waiting for clients");
                try {
                    Socket socket = serverSocket.accept();
                    Client client = new Client(socket);
                    client.start();
                    System.out.println("Client connected");
                } catch (IOException e) {}
            }
            for (Client client : clientList) {
                client.stop();
            }
        }
    }

    protected class Client extends Worker {
        protected Socket socket;
        protected ObjectInputStream objectInputStream;
        protected OutputStream outputStream;

        public Client(Socket socket) throws IOException {
            this.socket = socket;
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            //outputStream = socket.getOutputStream();
            clientList.add(this);
        }

        public void run() {
            try {
                Object object;
                do {
                    object = objectInputStream.readObject();
                    if (object instanceof Event) {
                        log.debug("event binnen!");
                        eventSpreader.add((Event) object);
                    }
                    log.debug("iets te lezen!");                    
                } while (object != null);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("stoppen");
            try {
                disconnect();
            } catch (IOException e) {
            } finally {
                clientList.remove(this);
            }
        }

        public void disconnect() throws IOException {
            objectInputStream.close();
            outputStream.close();
            socket.close();       
        }
    }
}
