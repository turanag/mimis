package mimis;

import mimis.util.swing.Dialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Client {
    protected Log log = LogFactory.getLog(getClass());

    public static final String IP = "127.0.0.1";
    public static final int PORT = 6789;

    public Client(String ip, int port) {
        //eventRouter = new GlobalRouter(ip, port);
    }

    public static void main(String[] args) {
        String ip = Dialog.question("Server IP:", IP);
        int port = Integer.valueOf(Dialog.question("Server Port:", PORT));
        //new Client(ip, port).start();
    }
}
