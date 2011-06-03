package mimis.application.cmd.windows;

import mimis.application.cmd.CMDApplication;
import mimis.exception.worker.ActivateException;
import mimis.util.Windows;
import mimis.value.Command;
import mimis.value.Key;
import mimis.value.Type;

public abstract class WindowsApplication extends CMDApplication {
    protected final static int TERMINATE_SLEEP = 500;
    protected final static int START_SLEEP = 500;

    protected String name;

    protected Process process;
    protected int handle;

    public WindowsApplication(String program, String title, String name) {
        super(program, title);
        this.name = name;
        handle = -1;
    }

    public void activate() throws ActivateException {
        handle = Windows.findWindow(name, null);
        if (handle < 1) {
            super.activate();
            sleep(START_SLEEP);
            handle = Windows.findWindow(name, null);
        }
        active = handle > 0;
        if (handle < 1) {
            throw new ActivateException();
        }
    }
    
    protected void command(Command command) {
        Windows.sendMessage(handle, Windows.WM_APPCOMMAND, handle, command.getCode() << 16);
    }

    protected void command(int command) {
        Windows.sendMessage(handle, Windows.WM_COMMAND, command, 0);
    }

    protected int user(int wParam, int lParam) {
        return Windows.sendMessage(handle, Windows.WM_USER, wParam, lParam);
        //return Windows.sendMessage(handle, Windows.WM_USER + wParam, 0, 0);
    }

    protected void key(Type key, int code) {
        int scanCode = Windows.mapVirtualKey(code, Windows.MAPVK_VK_TO_VSC);
        Windows.postMessage(handle, key.getCode(), code, 1 | (scanCode << 16));
    }

    protected void key(Type key, char character) {
        key(key, (int) Character.toUpperCase(character));
    }

    protected void key(Type key, Key virtualKey) {
        key(key, virtualKey.getCode());
    }
}
