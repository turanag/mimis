package mimis.util.swing;

import javax.swing.JOptionPane;

public class Dialog {
    public static final String TITLE = "MIMIS Dialog";

    public static String question(String message, Object initial) {
        return question(TITLE, message, initial);
    }
    
    public static String question(String title, String message, Object initial) {
        return (String) JOptionPane.showInputDialog(
            null, message, title,
            JOptionPane.QUESTION_MESSAGE,
            null, null, initial);
    }
}
