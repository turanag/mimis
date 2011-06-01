package pm.event.router;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import pm.Event;
import pm.Worker;
import pm.event.EventRouter;
import pm.event.Feedback;
import pm.exception.event.router.GlobalRouterException;

public class GlobalRouter extends EventRouter {
    protected Socket socket;
    protected ObjectOutputStream objectOutputStream;
    protected ObjectInputStream objectInputStream;

    public GlobalRouter(String ip, int port) throws GlobalRouterException {
        try {
            socket = new Socket(ip, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            new Worker() {
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
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            };
            return;
        } catch (UnknownHostException e) {
        } catch (IOException e) {}
        throw new GlobalRouterException();
    }

    public void event(Event event) {
        System.out.println("NetworkSpreader: event!");
        //System.out.println(socket.isConnected());
        try {
            objectOutputStream.writeObject(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
