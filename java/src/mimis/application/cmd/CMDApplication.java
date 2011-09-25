package mimis.application.cmd;

import java.io.IOException;
import java.util.Map;

import mimis.Application;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.util.Native;
import mimis.value.Registry;

public abstract class CMDApplication extends Application {
    protected final static Registry REGISTRY = Registry.LOCAL_MACHINE;
    protected final static String KEY = "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths";

    protected String program;
    protected String title;
    protected Process process;

    public CMDApplication(String program, String title) {
        super(title);
        this.program = program;
        this.title = title;
    }

    public void activate() throws ActivateException {
        super.activate();
        String path = getPath();
        if (path == null) {
            throw new ActivateException();
        }
        try {
            String command = path.startsWith("\"") ?  path : String.format("\"%s\"", path);
            command = replaceVariables(command);
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            log.error(e);
            throw new ActivateException();
        }
    }

    public boolean active() {
        return active = Native.isRunning(program);
    }

    protected synchronized void deactivate() throws DeactivateException  {
        super.deactivate();
        log.debug(process);
        if (process != null) {
            process.destroy();
        }
    }

    public String getPath() {
        String key = String.format("%s\\%s", KEY, program);
        System.out.println(Native.getValue(REGISTRY, key));
        return Native.getValue(REGISTRY, key);         
    }

    public static String replaceVariables(String string) {
        Map<String, String> env = System.getenv();
        for (String key : env.keySet()) {
            string = string.replace(String.format("%%%s%%", key), env.get(key));
        }
        return string;
    }
}
