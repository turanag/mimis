package mimis.device.lirc;

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
import java.util.NoSuchElementException;
import java.util.Scanner;

import mimis.Worker;
import mimis.exception.button.UnknownButtonException;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.util.Native;
import mimis.value.Registry;

public class LircService extends Worker {
    public static final String IP = "localhost";
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
    protected String send;

    public LircService() {
        this(IP, PORT);
        buttonMap = new HashMap<String, LircButton[]>();
        send = Native.getValue(Registry.CURRENT_USER, "Software\\LIRC", "password");
    }

    public LircService(String ip, int port) {
        this.ip = ip;
        this.port = port;
        lircButtonListenerList = new ArrayList<LircButtonListener>();
    }

    public void put(String name, LircButton[] LircButtonArray) {
        buttonMap.put(name, LircButtonArray);
    }

    public void add(LircButtonListener lircButtonListener) {
        lircButtonListenerList.add(lircButtonListener);
    }

    public void remove(LircButtonListener lircButtonListener) {
        lircButtonListenerList.remove(lircButtonListener);
    }

    public void activate() throws ActivateException {
        log.trace("Activate LircService");
        try {
            socket = new Socket(ip, port);

            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
        } catch (UnknownHostException e) {
            log.error(e);
            throw new ActivateException();
        } catch (IOException e) {
            log.error(e);
            throw new ActivateException();
        }
        super.activate();
    }

    public boolean active() {
        if (active && !socket.isConnected()) {
            active = false;
        }
        return active;
    }

    public void deactivate() throws DeactivateException {
        log.trace("Deactivate LircService");
        super.deactivate();
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    public void work() {
        try {
            String line = bufferedReader.readLine();
            while (line.equals("BEGIN")) {
                while (!bufferedReader.readLine().equals("END"));
                line = bufferedReader.readLine();
            }
            try {
                LircButton lircButton = parseButton(new Scanner(line));
                for (LircButtonListener lircbuttonListener : lircButtonListenerList) {
                    lircbuttonListener.add(lircButton);
                }
            } catch (UnknownButtonException e) {
                log.error(e);
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    public LircButton parseButton(Scanner scanner) throws UnknownButtonException {
        try {
            scanner.next();
            scanner.next();
            String code = scanner.next();
            String remote = scanner.next();
            log.trace(String.format("%s: %s", remote, code));
            LircButton[] buttonArray = buttonMap.get(remote);
            if (buttonArray != null) {            
                for (LircButton button : buttonArray) {
                    if (button.getCode().equals(code)) {
                        return button;
                    }
                }
            }
        } catch (InputMismatchException e) {
            log.error(e);
        } catch (NoSuchElementException e) {
            log.error(e);
        }
        throw new UnknownButtonException();
    }

    public void send(LircButton button) {
        send(button, 0);
    }

    public void send(LircButton button, int repeat) {
        if (send == null) {
            return;
        }
        String command = String.format("%s %s %s \n", send, button.getName(), button.getCode());
        printWriter.append(command);
        printWriter.flush();
    }
}
