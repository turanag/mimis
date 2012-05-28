package mimis.device.jintellitype;

import java.util.ArrayList;

import mimis.Button;
import mimis.value.Key;

import com.melloware.jintellitype.JIntellitype;


public class Hotkey implements Button {
    protected static final long serialVersionUID = 1L;

    protected static ArrayList<Hotkey> hotkeyList;
    protected static JIntellitype jit;

    public Hotkey(int modifier, int keycode) {
        int id = hotkeyList.size();
        jit.registerHotKey(id, modifier, keycode);
        hotkeyList.add(this);
    }

    public Hotkey(int modifier, char character) {
        this(modifier, (int) Character.toUpperCase(character));
    }

    public Hotkey(char character) {
        this(0, (int) Character.toUpperCase(character));
    }

    public Hotkey(int keycode) {
        this(0, keycode);
    }

    public Hotkey(Key key) {
        this(key.getCode());
    }

    public Hotkey(int modifier, Key key) {
        this(modifier, key.getCode());
    }

    public static void initialise(ArrayList<Hotkey> actionList, JIntellitype jit) {
        Hotkey.hotkeyList = actionList;
        Hotkey.jit = jit;
    }
}
