package mimis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class VBScript {
    public static boolean isRunning(String program) throws IOException {
        boolean found = false;
        File file = File.createTempFile("vbsutils", ".vbs");
        file.deleteOnExit();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(String.format(
              "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
            + "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
            + "Set service = locator.ConnectServer()\n"
            + "Set processes = service.ExecQuery _\n"
            + " (\"select * from Win32_Process where name='%s'\")\n"
            + "For Each process in processes\n"
            + "wscript.echo process.Name\n"
            + "Next\n"
            + "Set WSHShell = Nothing\n", program));
        fileWriter.close();
        Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
        InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
        BufferedReader input = new BufferedReader(inputStreamReader);
        String line = input.readLine();
        found = line != null && line.equals(program);
        input.close();
        return found;
    }

    public static void terminate(String program) throws IOException {
        File file = File.createTempFile("vbsutils", ".vbs");
        file.deleteOnExit();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(String.format(
              "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
            + "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
            + "Set service = locator.ConnectServer()\n"
            + "Set processes = service.ExecQuery _\n"
            + " (\"select * from Win32_Process where name='%s'\")\n"
            + "For Each process in processes\n"
            + "process.Terminate()\n"
            + "Next\n"
            + "Set WSHShell = Nothing\n", program));
        fileWriter.close();
        Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
    }
}
