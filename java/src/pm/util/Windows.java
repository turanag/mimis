package pm.util;

import com.eaio.nativecall.IntCall;

public class Windows {
    protected static IntCall findWindow;
    protected static IntCall sendMessage;
    protected static IntCall postMessage;
    protected static IntCall mapVirtualKey;

    static {
        findWindow = new IntCall("user32", "FindWindowA");
        sendMessage = new IntCall("user32", "SendMessageA");
        postMessage = new IntCall("user32", "PostMessageA");
        mapVirtualKey = new IntCall("user32", "MapVirtualKeyA");
    }

    public static int findWindow(String className, String windowName) {
        return findWindow.executeCall(new Object[] {className, windowName});
    }

    public static boolean postMessage(int handle, int message, int wParam, int lParam) {
        return postMessage.executeBooleanCall(new Object[] {handle, message, wParam, lParam});
    }
}
