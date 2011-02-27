package pm.application.cmd.windows;

import pm.application.cmd.CMDApplication;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.util.Windows;
import pm.value.Command;
import pm.value.Key;
import pm.value.Type;

abstract public class WindowsApplication extends CMDApplication {
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

    public void initialise() throws ApplicationInitialiseException {
        handle = Windows.findWindow(name, null);
        if (handle < 1) {
            super.initialise();
            sleep(START_SLEEP);
            handle = Windows.findWindow(name, null);
        }
        if (handle < 1) {
            throw new ApplicationInitialiseException();
        }
    }
    
    protected void command(Command command) {
        Windows.sendMessage(handle, Windows.WM_APPCOMMAND, handle, command.getCode() << 16);
    }

    protected void command(int command) {
        Windows.sendMessage(handle, Windows.WM_COMMAND, command, 0);
    }

    protected void user(int code) {
        Windows.sendMessage(handle, Windows.WM_USER + code, 0, 0);
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
