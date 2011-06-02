package mimis.device.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import mimis.Device;
import mimis.Event;
import mimis.Worker;
import mimis.event.Feedback;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class NetworkDevice extends Device {
    protected static final String TITLE = "Network";
    public static final int PORT = 6789;

    protected Log log = LogFactory.getLog(NetworkDevice.class);
    protected int port;
    protected Server server;
    protected ArrayList<Client> clientList;

    public NetworkDevice(int port) {
        super(TITLE);
        this.port = port;
    }

    public NetworkDevice() {
        this(PORT);
    }

    public void activate() {
        try {
            server = new Server(port);
            server.start();
        } catch (IOException e) {
            //throw new DeviceInitialiseException();
        }
    }

    public void deactivate() {
        server.stop();
    }

    protected void feedback(Feedback feedback) {
        for (Client client : clientList) {
            try {
                client.send(feedback);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected class Server extends Worker {
        protected ServerSocket serverSocket;

        public Server(int port) throws IOException {
            serverSocket = new ServerSocket(port);
            clientList = new ArrayList<Client>();
            System.out.println("Server started");
        }

        public void work() {
            System.out.println("Server is waiting for clients");
            try {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket);
                client.start();
                System.out.println("Client connected");
            } catch (IOException e) {}
        }
        
        public void stop() {
            for (Client client : clientList) {
                client.stop();
            }
            super.stop();
        }
    }

    protected class Client extends Worker {
        protected Socket socket;
        protected ObjectInputStream objectInputStream;
        protected ObjectOutputStream objectOutputStream;

        public Client(Socket socket) throws IOException {
            this.socket = socket;
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            clientList.add(this);
        }

        public void work() {
            try {
                Object object;
                do {
                    object = objectInputStream.readObject();
                    if (object instanceof Event) {
                        log.debug("event binnen!");
                        eventRouter.add((Event) object);
                    }
                    log.debug("iets te lezen!");                    
                } while (object != null);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            System.out.println("stoppen");
            try {
                disconnect();
            } catch (IOException e) {
            } finally {
                clientList.remove(this);
            }
        }

        public void send(Object object) throws IOException {
            objectOutputStream.writeObject(object);
        }

        public void disconnect() throws IOException {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();       
        }
    }
}
