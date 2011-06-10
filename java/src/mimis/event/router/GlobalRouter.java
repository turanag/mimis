package mimis.event.router;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import mimis.Event;
import mimis.Worker;
import mimis.event.EventRouter;
import mimis.event.Feedback;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;

public class GlobalRouter extends EventRouter {
    protected String ip;
    protected int port;
    protected Client client;

    public GlobalRouter(String ip, int port)  {
        this.ip = ip;
        this.port = port;
    }

    public void activate() throws ActivateException {
        try {
            client = new Client(ip, port);
        } catch (IOException e) {
            log.error(e);
            throw new ActivateException();
        }
        super.activate();
    }

    public boolean active() {
        if (active && client.active()) {
            active = false;
        }
        return active;
    }

    public void deactivate() throws DeactivateException {
        client.stop();
    }

    public void event(Event event) {
        try {
            client.send(event);
        } catch (IOException e) {
            log.error(e);
        }
    }

    class Client extends Worker {
        protected Socket socket;
        protected ObjectInputStream objectInputStream;
        protected ObjectOutputStream objectOutputStream;

        public Client(String ip, int port) throws IOException {
            socket = new Socket(ip, port);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }

        public void work() {
            try {
                Object object;
                do {
                    object = objectInputStream.readObject();
                    if (object instanceof Feedback) {
                        add((Feedback) object);
                    }                 
                } while (object != null);
            } catch (IOException e) {
                log.error(e);
            } catch (ClassNotFoundException e) {
                log.error(e);
            }
        }

        public boolean active() {
            return active = socket.isConnected();
        }

        public void stop() {
            try {
                objectInputStream.close();
                objectOutputStream.close();
                socket.close();      
            } catch (IOException e) {
                log.error(e);
            }
        }

        public void send(Object object) throws IOException {
            objectOutputStream.writeObject(object);
        }
    }
}
