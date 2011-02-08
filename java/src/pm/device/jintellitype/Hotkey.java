package pm.device.jintellitype;

import java.util.ArrayList;

import com.melloware.jintellitype.JIntellitype;

import pm.macro.event.Press;

public class Hotkey extends Press {
    protected static ArrayList<Hotkey> hotkeyList;
    protected static JIntellitype jit;

    public Hotkey(int modifier, int keycode) {
        super(null);
        int id = hotkeyList.size();
        button = new HotkeyButton(id);
        jit.registerHotKey(id, modifier, keycode);
        hotkeyList.add(this);
    }

    public Hotkey(int modifier, char keycode) {
        this(modifier, (int) keycode);
    }

    public Hotkey(char keycode) {
        this(0, (int) keycode);
    }

    public Hotkey(int keycode) {
        this(0, keycode);
    }

    public static void initialise(ArrayList<Hotkey> actionList, JIntellitype jit) {
        Hotkey.hotkeyList = actionList;
        Hotkey.jit = jit;
    }
}
