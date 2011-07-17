package mimis.application.cmd.windows;

import mimis.application.cmd.CMDApplication;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.util.Native;
import mimis.value.Command;
import mimis.value.Key;
import mimis.value.Type;

public abstract class WindowsApplication extends CMDApplication {
    protected final static int TERMINATE_SLEEP = 500;
    protected final static int START_SLEEP = 500;

    public static final int WM_CLOSE = 0x0010;
    public static final int WM_COMMAND = 0x0111;
    public static final int WM_APPCOMMAND = 0x0319;
    public static final int WM_USER = 0x0400;
    public static final int MAPVK_VK_TO_VSC = 0;

    protected String window;
    protected Process process;
    protected int handle;

    public WindowsApplication(String program, String title, String window) {
        super(program, title);
        this.window = window;
        handle = 0;
    }

    public void activate() throws ActivateException {
        super.activate();
        handle = Native.getHandle(window);
        if (handle < 1) {
            sleep(START_SLEEP);
            handle = Native.getHandle(window);
        }
        active = handle > 0;
        if (!active) {
            throw new ActivateException();
        }
    }

    public boolean active() {
        if (!active) {
            handle = Native.getHandle(window);
        }
        return super.active();
    }

    public void deactivate() throws DeactivateException {
        if (!Native.terminate(program)) {
            throw new DeactivateException();
        }
    }

    protected void command(Command command) {
        Native.sendMessage(handle, WM_APPCOMMAND, handle, command.getCode() << 16);
    }

    protected void command(int command) {
        Native.sendMessage(handle, WM_COMMAND, command, 0);
    }

    protected int user(int wParam, int lParam) {
        return Native.sendMessage(handle, WM_USER, wParam, lParam);
        //return Windows.sendMessage(handle, Windows.WM_USER + wParam, 0, 0);
    }

    protected void key(Type type, int code) {
        int scanCode = Native.mapVirtualKey(code, MAPVK_VK_TO_VSC);
        Native.postMessage(handle, type.getCode(), code, 1 | (scanCode << 16));
    }

    protected void key(Type type, char character) {
        key(type, (int) Character.toUpperCase(character));
    }

    protected void key(Type key, Key virtualKey) {
        key(key, virtualKey.getCode());
    }
}
