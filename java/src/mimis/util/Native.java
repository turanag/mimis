package mimis.util;

import mimis.value.Registry;
import mimis.value.Windows;

public class Native {
    static {
        System.loadLibrary("mimis");
    }

    public void start() {
        int handle = getHandle("Winamp v1.x");
        System.out.println(handle);
        sendMessage(handle, Windows.WM_CLOSE, 0, 0);
        /*
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
        }*/
    }

    public static void main(String[] args) {
        new Native().start();
    }

    public native static int getHandle(String window);

    public static int sendMessage(int handle, Windows windows, int wParam, int lParam) {
        return sendMessage(handle, windows.getCode(), wParam, lParam);
    }

    public native static int sendMessage(int handle, int message, int wParam, int lParam);

    public static int postMessage(int handle, Windows windows, int wParam, int lParam) {
        return postMessage(handle, windows.getCode(), wParam, lParam);
    }

    public native static int postMessage(int handle, int message, int wParam, int lParam);
    
    public static int mapVirtualKey(int code, Windows windows) {
        return mapVirtualKey(code, windows.getCode());
    }

    public native static int mapVirtualKey(int code, int type);

    public native static boolean isRunning(String program);

    public native static boolean terminate(String program);

    public static String getValue(Registry registry, String key) {
        return getValue(registry, key, "");
    }

    public static String getValue(Registry registry, String key, String name) {
        return getValue(registry.getCode(), key, name);
    }

    public native static String getValue(int registry, String key, String name);

}
