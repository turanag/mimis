package mimis.router;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.input.Feedback;
import mimis.input.Task;
import mimis.worker.Worker;

public class GlobalRouter extends Router {
    protected String ip;
    protected int port;
    protected Client client;

    public GlobalRouter(String ip, int port)  {
        this.ip = ip;
        this.port = port;
    }

    protected void activate() throws ActivateException {
        try {
            client = new Client(ip, port);
        } catch (IOException e) {
            log.error(e);
            throw new ActivateException();
        }
        super.activate();
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        client.stop();
    }

    public void task(Task task) {
        try {
            client.send(task);
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

        protected void deactivate() throws DeactivateException {
            super.deactivate();
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
