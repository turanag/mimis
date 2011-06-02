package pm.device.lirc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import pm.Worker;
import pm.exception.button.UnknownButtonException;

public class LircService extends Worker {
    public static final String IP = "127.0.0.1";
    public static final int PORT = 8765;

    protected ArrayList<LircButtonListener> lircButtonListenerList;
    protected String ip;
    protected int port;
    protected Socket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected BufferedReader bufferedReader;
    protected PrintWriter printWriter;
    protected HashMap<String, LircButton[]> buttonMap;

    public LircService(HashMap<String, LircButton[]> buttonMap) {
        this(buttonMap, IP, PORT);
    }

    public LircService(HashMap<String, LircButton[]> buttonMap, String ip, int port) {
        this.buttonMap = buttonMap;
        this.ip = ip;
        this.port = port;
        lircButtonListenerList = new ArrayList<LircButtonListener>();
    }

    public void add(LircButtonListener lircButtonListener) {
        lircButtonListenerList.add(lircButtonListener);
    }

    public void remove(LircButtonListener lircButtonListener) {
        lircButtonListenerList.remove(lircButtonListener);
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
        super.activate();
    }

    public void deactivate() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        super.deactivate();
    }

    public void work() {
        try {
            String string = bufferedReader.readLine();
            try {
                LircButton lircButton = parseButton(new Scanner(string));
                log.debug(String.format("Lirc button: %s", lircButton));
                for (LircButtonListener lircbuttonListener : lircButtonListenerList) {
                    lircbuttonListener.add(lircButton);
                }
            } catch (UnknownButtonException e) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LircButton parseButton(Scanner scanner) throws UnknownButtonException {
        try {
            scanner.next();
            scanner.next();
            String code = scanner.next();
            String remote = scanner.next();
            //log.debug(String.format("%s: %s", remote, code));            
            LircButton[] buttonArray = buttonMap.get(remote);
            if (buttonArray != null) {            
                for (LircButton button : buttonArray) {
                    if (button.getCode().equals(code)) {
                        return button;
                    }
                }
            }
        } catch (InputMismatchException e) {}
        throw new UnknownButtonException();
    }

    public static void main(String[] args) {
        System.out.println("hey");
        //String send = Native.getValue("HKEY_CURRENT_USER\\Software\\LIRC", "password");
        new LircDevice().start();
        while (true);
    }
}
