package mimis.application.cmd;

import java.io.IOException;

import mimis.Application;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.util.Native;
import mimis.util.VBScript;


public abstract class CMDApplication extends Application {
    protected final static String REGISTRY = "HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths";

    protected String program;
    protected String title;
    protected Process process;

    public CMDApplication(String program, String title) {
        super(title);
        this.program = program;
        this.title = title;
    }

    public String getPath() {
        String key = String.format("%s\\%s", REGISTRY, program);
        return Native.getValue(key);         
    }

    public void activate() throws ActivateException {
        super.activate();
        String path = getPath();
        if (path == null) {
            throw new ActivateException();
        }
        try {
            String command = path.startsWith("\"") ?  path : String.format("\"%s\"", path);
            command = Native.replaceVariables(command);
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new ActivateException();
        }
    }

    public boolean active() {
        try {
            return active = VBScript.isRunning(program);
        } catch (IOException e) {
            return active = false;
        }
    }

    public void deactivate() throws DeactivateException {
        super.deactivate();
        if (process != null) {
            process.destroy();
        }
    }
}
