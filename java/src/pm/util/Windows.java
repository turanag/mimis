package pm.util;

import com.eaio.nativecall.IntCall;
import com.eaio.nativecall.NativeCall;

public class Windows {
    public static final int WM_COMMAND = 0x0111;
    public static final int WM_APPCOMMAND = 0x0319;
    public static final int MAPVK_VK_TO_VSC = 0;
    public static final int WM_USER = 0x0400;

    protected static IntCall findWindow;
    protected static IntCall sendMessage;
    protected static IntCall postMessage;
    protected static IntCall mapVirtualKey;

    static {
        try {
            NativeCall.init();
            findWindow = new IntCall("user32", "FindWindowA");
            sendMessage = new IntCall("user32", "SendMessageA");
            postMessage = new IntCall("user32", "PostMessageA");
            mapVirtualKey = new IntCall("user32", "MapVirtualKeyA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int findWindow(String className, String windowName) {
        return findWindow.executeCall(new Object[] {className, windowName});
    }

    public static int postMessage(int handle, int message, int wParam, int lParam) {
        return postMessage.executeCall(new Object[] {handle, message, wParam, lParam});
    }

    public static int sendMessage(int handle, int message, int wParam, int lParam) {
        return sendMessage.executeCall(new Object[] {handle, message, wParam, lParam});
    }

    public static int mapVirtualKey(int code, int mapType) {
        return mapVirtualKey.executeCall(new Object[] {code, 0});
    }
}
