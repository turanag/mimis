package pm.device.jintellitype;

import java.util.ArrayList;

import com.melloware.jintellitype.JIntellitype;

import pm.macro.state.Press;
import pm.value.Key;

public class Hotkey extends Press {
    protected static ArrayList<Hotkey> hotkeyList;
    protected static JIntellitype jit;

    public Hotkey(int modifier, int keycode) {
        super(null); // Todo: nettere oplossing zoeken / controleren op null
        int id = hotkeyList.size();
        button = new Modifier(id);
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
