package pm.application.windows;

import java.io.IOException;

import pm.Application;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.exception.application.windows.SendCommandException;
import pm.exception.application.windows.SendKeyException;
import pm.util.Native;
import pm.util.Windows;

import com.eaio.nativecall.IntCall;
import com.eaio.nativecall.NativeCall;

abstract public class WindowsApplication extends Application {
    protected final static int TERMINATE_SLEEP = 500;
    protected final static int START_SLEEP = 500;

    protected final static String REGISTRY = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths";

    protected final static int WM_COMMAND = 0x0111;
    protected final static int WM_APPCOMMAND = 0x0319;

    protected String program;
    protected String title;
    protected String name;
    protected String target;

    protected Process process;
    protected int handle;

    protected IntCall sendMessage;
    protected IntCall postMessage;
    protected IntCall mapVirtualKey;

    static {
        try {
            NativeCall.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WindowsApplication(String program, String title, String name) {
        this.program = program;
        this.title = title;
        this.name = name;
        handle = -1;
    }

    public void initialise() throws ApplicationInitialiseException {
        sendMessage = new IntCall("user32", "SendMessageA");
        postMessage = new IntCall("user32", "PostMessageA");
        mapVirtualKey = new IntCall("user32", "MapVirtualKeyA");

        handle = Windows.findWindow(name, null);
        if (handle < 1) {
            String key = String.format("%s\\%s", REGISTRY, program);
            String path = Native.getValue(key);
            try {
                String command = String.format("\"%s\"", path);
                process = Runtime.getRuntime().exec(command);
                sleep(START_SLEEP);
                handle = Windows.findWindow(name, null);
            } catch (IOException e) {}
        }
        if (handle < 1) {
            throw new ApplicationInitialiseException();
        }
    }

    public void exit() throws ApplicationExitException {
        if (process != null) {
            process.destroy();
        }
        super.exit();
    }

    protected void command(Command command) throws SendCommandException {
        int result = sendMessage.executeCall(new Object[] {
            handle, WM_APPCOMMAND, handle, command.getCode() << 16});
        if (result < 1 || sendMessage.getLastError() != null) {
            throw new SendCommandException();
        }
    }

    protected void command(int command) throws SendCommandException {
        int result = sendMessage.executeCall(new Object[] {
                handle, WM_COMMAND, command});
            if (result < 1 || sendMessage.getLastError() != null) {
                throw new SendCommandException();
            }
    }

    protected void key(Type key, int code) throws SendKeyException {
        int scanCode = mapVirtualKey.executeCall(new Object[] {code, 0});
        int result = postMessage.executeCall(new Object[] {
            handle, key.getCode(), code, 1 | (scanCode << 16)});
        if (result < 1 || postMessage.getLastError() != null) {
            throw new SendKeyException();
        }
    }

    protected void key(Type key, char character) throws SendKeyException {
        key(key, (int) Character.toUpperCase(character));
    }

    protected void key(Type key, Key virtualKey) throws SendKeyException {
        key(key, virtualKey.getCode());
    }
}
