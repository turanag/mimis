package pm.application.vlc;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import pm.application.cmd.CMDApplication;
import pm.exception.application.ApplicationInitialiseException;
import pm.value.Action;

public class VLCApplication extends CMDApplication {
    protected final static String PROGRAM = "vlc.exe";
    protected final static String TITLE = "VLC media player";

    protected static final String HOST = "127.0.0.1"; // localhost
    protected static final int PORT = 8080;
    protected Socket socket;
    Scanner input;
    PrintStream output;
    Scanner feedback;
    
    public VLCApplication() {
        super(PROGRAM, TITLE);
    }

    public void initialise() throws ApplicationInitialiseException {
        super.initialise();
        connect();
        test();
    }

    public void connect() {
        System.out.println("Connecting to VLC");
        try {
            socket = new Socket(HOST, PORT);
            input = new Scanner(System.in);
            output = new PrintStream(socket.getOutputStream());
            feedback = new Scanner(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connection established");
    }
    
    public void test() {
        output.println("GET /requests/status.xml?command=volume&val=-20 HTTP/1.1\r\n");
        while (feedback.hasNext()) {
            String message = feedback.next();
            System.out.printf("%s", message);
        }
    }

    public void action(Action action) {
        System.out.println("VLCApplication: " + action);/*
        switch (action) {
            case PLAY:
                command(18808);
                break;
            case NEXT:
                command(18811);
                break;
            case PREVIOUS:
                command(18810);
                break;
            case FORWARD:
                command(18813);
                break;
            case REWIND:
                command(18814);
                break;
            case MUTE:
                command(18817);
                break;
            case VOLUME_UP:
                command(18815);
                break;
            case VOLUME_DOWN:
                command(18816);
                break;
            case SHUFFLE:
                command(18842);
                break;
            case REPEAT:
                command(18843);
                break;
        }*/
    }
}
