package mimis.util;

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Native {
    static {
        System.loadLibrary("mimis");
    }

    public void start() {
        /*int handle = getHandle("Winamp v1.x");
        System.out.println(handle);
        sendMessage(handle, WindowsApplication.WM_CLOSE, 0, 0);
        /*/
        while (true) {//Winamp v1.x
            System.out.println(isRunning("winamp.exe"));
            //System.out.println(new Native().terminate("winamp.exe"));
            //System.out.println(new Native().running("wmplayer.exe"));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Native().start();
    }

    public native static int getHandle(String window);
    public native static int sendMessage(int handle, int message, int wParam, int lParam);
    public native static int postMessage(int handle, int message, int wParam, int lParam);
    public static native int mapVirtualKey(int code, int type);    
    public native static boolean isRunning(String program);
    public native static boolean terminate(String program);

    public static String getValue(String key, String name) {
        String command = String.format("reg query \"%s\"", key);
        try {
            Process process = Runtime.getRuntime().exec(command);
            Scanner processScanner = new Scanner(process.getInputStream());
            processScanner.nextLine();
            processScanner.nextLine();
            while (processScanner.hasNextLine()) {
                String line = processScanner.nextLine();
                int index = line.indexOf(name);
                if (index > -1) {
                    int begin = index + name.length() + 1;
                    Scanner lineScanner = new Scanner(line.substring(begin));
                    lineScanner.next();
                    return lineScanner.nextLine().trim();
                }
            }
        } catch (IOException e) {
        } catch (NoSuchElementException e) {}
        return null;
    }

    public static String getValue(String key) {
        return getValue(key, "(Default)");
    }

    public static String replaceVariables(String string) {
        Map<String, String> env = System.getenv();
        for (String key : env.keySet()) {
            string = string.replace(String.format("%%%s%%", key), env.get(key));
        }
        return string;
    }
}
