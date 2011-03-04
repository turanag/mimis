package pm.application.vlc;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import pm.application.cmd.CMDApplication;
import pm.exception.application.ApplicationInitialiseException;
import pm.value.Action;

public class VLCApplication extends CMDApplication {
    protected final static String PROGRAM = "vlc.exe";
    protected final static String TITLE = "VLC media player";
    
    protected static final int POSTION_CHANGE_RATE = 1;
    protected static final int VOLUME_CHANGE_RATE = 20;

    protected static final String HOST = "127.0.0.1"; // localhost
    protected static final int PORT = 8080;
    protected Socket socket;
    PrintStream output;
    Scanner feedback;
    
    public VLCApplication() {
        super(PROGRAM, TITLE);
    }

    public void initialise() throws ApplicationInitialiseException {
        super.initialise();
        connect();
    }

    public void connect() {
        System.out.println("Connecting to VLC");
        try {
            socket = new Socket(HOST, PORT);
            output = new PrintStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connection established");
    }
    
    public void command(String command) {
        //String request = "GET /requests/status.xml?command=" + command + " /HTTP/1.1\r\n\n";
        output.println(request);
        //System.out.printf("%s", request);
        /*System.out.println(request);
        try {
            Scanner feedback = new Scanner(socket.getInputStream());
            while(feedback.hasNext()) {
                System.out.printf("!!!%s!!!", feedback.nextLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        //System.out.println("GET /requests/status.xml?command=" + command + " /HTTP/1.1\r\n");
        // Voorbeeld
        // GET /requests/status.xml?command=volume&val=+20 HTTP/1.1
    }
    
    public void action(Action action) {
        System.out.println("VLCApplication: " + action);
        switch (action) {
            case PLAY:
                command("pl_pause");
                break;
            case PAUSE:
                command("pl_pause");
                break;
            case NEXT:
                command("pl_next");
                break;
            case PREVIOUS:
                command("pl_previous");
                break;
            case FORWARD:
                command("command=seek&val=+" + POSTION_CHANGE_RATE);
                break;
            case REWIND:
                command("command=seek&val=-" + POSTION_CHANGE_RATE);
                break;
            case MUTE:
                /*
                 * Nog implementeren
                 * command=volume&val=
                 */
                break;
            case VOLUME_UP:
                command("volume&val=+" + VOLUME_CHANGE_RATE);
                break;
            case VOLUME_DOWN:
                command("volume&val=-" + VOLUME_CHANGE_RATE);
                break;
            case SHUFFLE:
                command("command=pl_random");
                break;
            case REPEAT:
                command("command=pl_repeat");
                break;
        }
    }
}
