package mimis.application.cmd;

import java.io.IOException;

import mimis.Application;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.util.Native;
import mimis.util.VBScript;


public abstract class CMDApplication extends Application {
    protected final static String REGISTRY = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths";

    protected String program;
    protected String title;
    protected Process process;

    public CMDApplication(String program, String title) {
        super(title);
        this.program = program;
        this.title = title;
    }

    public void activate() throws ActivateException {
        String key = String.format("%s\\%s", REGISTRY, program);
        // Check of naam is gevonden in register
        String path = Native.getValue(key);
        try {
            String command = path.startsWith("\"") ?  path : String.format("\"%s\"", path);
            command = Native.replaceVariables(command);
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new ActivateException();
        }
        super.activate();
    }

    public boolean active() {
        try {
            return active = VBScript.isRunning(program);
        } catch (IOException e) {
            return active = false;
        }
    }

    public void deactivate() throws DeactivateException {
        if (process != null) {
            process.destroy();
        }
        super.deactivate();
    }
}
