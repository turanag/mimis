package mimis.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

public class Swing {
    protected static ClassLoader classLoader;
    protected static Toolkit toolkit;

    static {
        classLoader = Swing.class.getClassLoader();
        toolkit = Toolkit.getDefaultToolkit();
    }

    public static URL getResource(String name) {
        return classLoader.getResource(name);
    }

    public static Image getImage(String name) {
        return toolkit.getImage(getResource(name));
    }

    public static ImageIcon getImageIcon(String name) {
        return new ImageIcon(getResource(name));
    }
}
