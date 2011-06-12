package mimis.device.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import mimis.Device;
import mimis.Event;
import mimis.Worker;
import mimis.event.Feedback;
import mimis.event.Task;
import mimis.event.feedback.TextFeedback;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.value.Action;
import mimis.value.Target;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NetworkDevice extends Device {
    protected static final String TITLE = "Network";
    public static final int PORT = 6789;

    protected Log log = LogFactory.getLog(NetworkDevice.class);
    protected int port;
    protected Server server;
    protected ConcurrentLinkedQueue<Client> clientList;

    public NetworkDevice(int port) {
        super(TITLE);
        this.port = port;
        clientList = new ConcurrentLinkedQueue<Client>();
    }

    public NetworkDevice() {
        this(PORT);
    }

    public void activate() throws ActivateException {
        log.trace("Activate NetworkDevice");
        try {
            server = new Server(port);
            server.activate();
        } catch (IOException e) {
            throw new ActivateException();
        }
        super.activate();
    }

    public boolean active() {
        for (Client client : clientList) {
            if (!client.active()) {
                client.stop();
            }
        }
        return server == null ? active : (active = server.active());
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        server.stop();
    }

    protected void feedback(Feedback feedback) {
        for (Client client : clientList) {
            client.send(feedback);
        }
    }

    protected class Server extends Worker {
        protected ServerSocket serverSocket;

        public Server(int port) throws IOException {
            serverSocket = new ServerSocket(port);
            eventRouter.add(new TextFeedback("[NetworkDevice] Wating for clients"));
        }

        public boolean active() {
            return active = !serverSocket.isClosed();
        }

        public void work() {
            try {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket);
                try {
                    client.activate();
                } catch (ActivateException e) {
                    log.error(e);
                }
                eventRouter.add(new TextFeedback("[NetworkDevice] Client connected: " + socket.getInetAddress()));
            } catch (IOException e) {
                log.error(e);
            }
        }

        public void stop() {
            super.stop();
            try {
                serverSocket.close();
            } catch (IOException e) {
                log.error(e);
            }
            for (Client client : clientList) {
                client.stop();
            }
        }
    }

    protected class Client extends Worker {
        protected Socket socket;
        protected InputStream inputStream;
        protected OutputStream outputStream;
        protected ObjectOutputStream objectOutputStream;

        public Client(Socket socket) throws IOException {
            this.socket = socket;
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            clientList.add(this);
        }

        public boolean active() {
            return active = socket.isConnected();
        }

        public void work() {
            ObjectInputStream objectInputStream;
            try {
                objectInputStream = new ObjectInputStream(inputStream);
                Object object;
                do {
                    object = objectInputStream.readObject();
                    if (object instanceof Event) {
                        log.trace(object);
                        eventRouter.add((Event) object);
                    }
                } while (object != null);
            } catch (IOException e) {
                log.error(e);
                stop();
            } catch (ClassNotFoundException e) {
                log.error(e);
            }
        }

        public void stop() {
            super.stop();
            send(new Task(Target.SELF, Action.STOP));
            clientList.remove(this);
            try {
                inputStream.close();
                outputStream.close();
                socket.close();    
            } catch (IOException e) {
                log.error(e);
            }
            eventRouter.add(new TextFeedback("[NetworkDevice] Client disconnected: " + socket.getInetAddress()));
        }

        public void send(Object object) {
            try {
                objectOutputStream.writeObject(object);
            } catch (IOException e) {
                log.error(e);
            }
        }
    }
}
