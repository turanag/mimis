package mimis.util;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Native {
    public static int getHandle(String title) throws IOException {
        String command = String.format("list.exe w");
        Process process = Runtime.getRuntime().exec(command);
        Scanner scanner = new Scanner(process.getInputStream());
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            Scanner line = new Scanner(scanner.nextLine());
            line.useDelimiter("\t");
            try {
                int handle = line.nextInt();
                line.nextInt();
                if (line.hasNext() && line.next().equals(title)) {
                    return handle;
                }
            } catch (InputMismatchException e) {}
        }
        return -1;
    }

    public static String getProgram(int processId) throws IOException {
        String command = String.format("list.exe p");
        Process process = Runtime.getRuntime().exec(command);
        Scanner scanner = new Scanner(process.getInputStream());
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            Scanner line = new Scanner(scanner.nextLine());
            line.useDelimiter("\t");
            try {
                if (line.nextInt() == processId) {
                    return line.next();
                }
            } catch (InputMismatchException e) {}
        }
        return null;
    }

    public static String getValue(String key, String name) {
        String command = String.format("reg query \"%s\"", key);
        try {
            Process process = Runtime.getRuntime().exec(command);
            Scanner processScanner = new Scanner(process.getInputStream());
            processScanner.nextLine();
            processScanner.nextLine();
            while (processScanner.hasNextLine()) {
                String line = processScanner.nextLine();
                int index = line.indexOf(name);
                if (index > -1) {
                    int begin = index + name.length() + 1;
                    Scanner lineScanner = new Scanner(line.substring(begin));
                    lineScanner.next();
                    return lineScanner.nextLine().trim();
                }
            }
        } catch (IOException e) {
        } catch (NoSuchElementException e) {}
        return null;
    }

    public static String getValue(String key) {
        return getValue(key, "(Default)");
    }

    public static String replaceVariables(String string) {
        Map<String, String> env = System.getenv();
        for (String key : env.keySet()) {
            string = string.replace(String.format("%%%s%%", key), env.get(key));
        }
        return string;
    }
}
