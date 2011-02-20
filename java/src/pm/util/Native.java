package pm.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Native {
    public static int getHandle(String name) throws IOException {
        File file = new File("native/list.exe");
        Process process = Runtime.getRuntime().exec(file.getPath());
        Scanner scanner = new Scanner(process.getInputStream());
        ArrayList<Integer> handleList = new ArrayList<Integer>();
        ArrayList<String> titleList = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            try {
                int handle = new Integer(scanner.nextLine());
                String title = scanner.nextLine();
                if (title.contains(name)) {
                    handleList.add(handle);
                    titleList.add(title);
                }
            } catch (InputMismatchException e) {}
        }
        int count = handleList.size();
        if (count == 1) {
            return handleList.get(0);
        }
        for (int i = 0; i < count; ++i) {
            if (titleList.get(i).endsWith(name)) {
                return handleList.get(i);
            }
        }
        return count > 0 ? handleList.get(0) : -1;
    }
    
    public static String getValue(String key, String name) throws IOException {
        Process process = Runtime.getRuntime().exec("reg query " + key);
        Scanner processScanner = new Scanner(process.getInputStream());
        try {
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
        } catch (NoSuchElementException e) {}
        return null;
    }

    public static String getValue(String key) {
        try {
            return getValue(key, "(Default");
        } catch (IOException e) {}
        return null;
    }
}
