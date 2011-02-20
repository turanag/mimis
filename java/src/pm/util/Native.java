package pm.util;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Native {
    public static int getHandle(String name) throws IOException {
        File file = new File("native/list.exe");
        Process process = Runtime.getRuntime().exec(file.getPath());
        Scanner scanner = new Scanner(process.getInputStream());
        while (scanner.hasNextLine()) {
            try {
            int handle = new Integer(scanner.nextLine());
            String title = scanner.nextLine();
            if (title.contains(name)) {
                System.out.println("Window (" + handle + "): \"" + title + "\"");
                return handle;
            }
            } catch (InputMismatchException e) {}
        }
        return -1;
    }
}
