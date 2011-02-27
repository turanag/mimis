package pm.application.cmd;

import java.io.IOException;

import pm.Application;
import pm.exception.application.ApplicationExitException;
import pm.exception.application.ApplicationInitialiseException;
import pm.util.Native;

public class CMDApplication extends Application {
    protected final static String REGISTRY = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths";

    protected String program;
    protected String title;
    protected Process process;

    public CMDApplication(String program, String title) {
        this.program = program;
        this.title = title;
    }

    public void initialise() throws ApplicationInitialiseException {
        String key = String.format("%s\\%s", REGISTRY, program);
        String path = Native.getValue(key);
        try {
            String command = path.startsWith("\"") ?  path : String.format("\"%s\"", path);
            command = Native.replaceVariables(command);
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApplicationInitialiseException();
        }
    }

    public void exit() throws ApplicationExitException {
        if (process != null) {
            process.destroy();
        }
        super.exit();
    }
}
