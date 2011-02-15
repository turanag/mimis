package pm.device.lantextinput;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class LanTextClient {
    static final String HOST = "127.0.0.1"; //localhost
    static final int PORT = 1234;

    protected boolean run;
    protected Socket socket;
    protected Scanner input;
    protected PrintStream output;

    LanTextClient(){
        try {
            socket = new Socket(HOST, PORT);
            input = new Scanner(System.in);
            output = new PrintStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
    protected void start(){      
       while(run){
            output.println(input.nextLine());
       }
    }
    
    public static void main(String[] argv) {
        new LanTextClient().start();
    }
}
