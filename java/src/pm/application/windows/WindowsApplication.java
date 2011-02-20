package pm.application.windows;

import java.io.IOException;

import com.eaio.nativecall.IntCall;
import com.eaio.nativecall.NativeCall;

import pm.Application;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.exception.application.windows.SendCommandException;
import pm.exception.application.windows.SendKeyException;
import pm.util.Native;
import pm.util.VBScript;

abstract public class WindowsApplication extends Application {
    protected final static int TERMINATE_SLEEP = 500;
    protected final static int START_SLEEP = 500;

    protected final static int WM_APPCOMMAND = 0x0319;
    protected final static int WM_KEYDOWN    = 0x0100;

    protected String path;
    protected String program;
    protected String name;
    protected String target;

    protected Process process;
    protected int handle;
    protected IntCall sendMessage;
    protected IntCall postMessage;

    static {
        try {
            NativeCall.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WindowsApplication(String path, String program, String name) {
        this.path = path;
        this.program = program;
        this.name = name;
        target = path + program;
        handle = -1;
    }

    public void initialise() throws ApplicationInitialiseException {
        try {
            if (VBScript.isRunning(program)) {
                handle = Native.getHandle(name);
                if (handle < 0) {
                    while (VBScript.isRunning(program)) {
                        VBScript.terminate(program);
                        sleep(TERMINATE_SLEEP);
                    }
                }
            }
            if (handle < 0) {
                process = Runtime.getRuntime().exec(target);
                while (!VBScript.isRunning(program)) {
                    sleep(START_SLEEP);
                }
                IntCall findWindow = new IntCall("user32", "FindWindowA");
                handle = findWindow.executeCall(new Object[] {null, name});
            }
        } catch (IOException e) {}
        if (handle < 1) {
            throw new ApplicationInitialiseException();
        }
        sendMessage = new IntCall("user32", "SendMessageA");
        postMessage = new IntCall("user32", "PostMessageA");
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

    protected void key(int key) throws SendKeyException {
        int result = postMessage.executeCall(new Object[] {
            handle, WM_KEYDOWN, key});
        if (result < 1 || postMessage.getLastError() != null) {
            throw new SendKeyException();
        }
    }

    protected void key(char key) throws SendKeyException {
        key((int) Character.toUpperCase(key));
    }

    protected void key(VirtualKey virtualKey) throws SendKeyException {
        key(virtualKey.getCode());
    }
}
