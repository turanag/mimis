package pm.device.jintellitype;

import java.util.ArrayList;

import com.melloware.jintellitype.JIntellitype;

import pm.Button;
import pm.value.Key;

public class Hotkey implements Button {
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

    public static void initialise(ArrayList<Hotkey> actionList, JIntellitype jit) {
        Hotkey.hotkeyList = actionList;
        Hotkey.jit = jit;
    }
}
