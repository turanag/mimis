package pm.event.spreader;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import pm.Event;
import pm.event.EventSpreader;
import pm.exception.event.spreader.NetworkSpreaderException;

public class NetworkSpreader extends EventSpreader {
    protected Socket socket;
    protected ObjectOutputStream objectOutputStream;

    public NetworkSpreader(String ip, int port) throws NetworkSpreaderException {
        try {
            socket = new Socket(ip, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            return;
        } catch (UnknownHostException e) {
        } catch (IOException e) {}
        throw new NetworkSpreaderException();
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
