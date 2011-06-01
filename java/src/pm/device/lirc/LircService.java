package pm.device.lirc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import pm.Worker;

public class LircService extends Worker {
    public static final String IP = "127.0.0.1";
    public static final int PORT = 6789;

    protected String ip;
    protected int port;
    protected Socket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected BufferedReader bufferedReader;
    protected PrintWriter printWriter;

    public LircService() {
        this(IP, PORT);
    }

    public LircService(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void activate() {
        try {
            socket = new Socket(ip, port);            

            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deactivate() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.deactivate();
    }

    public void work() {
        try {
            String string = bufferedReader.readLine();
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
