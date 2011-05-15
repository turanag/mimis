package pm;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;

public class Test {
    public void start() throws IOException {
        Class<Application> application = Application.class;
        Class<?> test = application.getEnclosingClass();
        System.out.println(test);
    }

    public static void main(String[] argv) throws IOException {
        new Test().start();
    }
}
