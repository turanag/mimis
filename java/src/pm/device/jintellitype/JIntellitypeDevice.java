package pm.device.jintellitype;

import java.util.ArrayList;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.IntellitypeListener;
import com.melloware.jintellitype.JIntellitype;

import pm.Device;
import pm.event.Task;
import pm.exception.button.UnknownButtonException;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.state.Press;
import pm.macro.state.Release;
import pm.value.Action;
import pm.value.Key;
import pm.value.Target;

public class JIntellitypeDevice extends Device implements HotkeyListener, IntellitypeListener {
    protected ArrayList<Hotkey> hotkeyList;
    protected JIntellitype jit;

    public JIntellitypeDevice() {
        hotkeyList = new ArrayList<Hotkey>();
        jit = JIntellitype.getInstance();
        Hotkey.initialise(hotkeyList, jit);
    }

    public void initialise() throws DeviceInitialiseException {
        jit.addHotKeyListener(this);
        jit.addIntellitypeListener(this);
        add(
            new Hotkey(Key.PRIOR),
            new Task(Target.MANAGER, Action.PREVIOUS));
        add(
            new Hotkey(Key.NEXT),
            new Task(Target.MANAGER, Action.NEXT));
        add(
            new Press(CommandButton.VOLUME_DOWN),
            new Task(Target.APPLICATIONS, Action.VOLUME_DOWN));
        add(
            new Press(CommandButton.VOLUME_UP),
            new Task(Target.APPLICATIONS, Action.VOLUME_UP));
        add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 'x'),
            new Task(Target.MANAGER, Action.EXIT));
        add(
            new Hotkey(Modifier.CTRL | Modifier.SHIFT | Modifier.WIN, 'n'),
            new Task(Target.APPLICATION, Action.NEXT));
        add(
            new Hotkey(Modifier.CTRL | Modifier.SHIFT | Modifier.WIN, 'p'),
            new Task(Target.APPLICATION, Action.PREVIOUS));
        /*add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 't'),
            new Task(Action.TEST, Target.MAIN));
        add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 'r'),
            new Hotkey(Modifier.CTRL | Modifier.WIN, 's'),
            new Continuous(Action.REPEAT, Target.APPLICATIONS, 500));*/
    }

    protected void add(Hotkey hotkey, Task task) {
        add(new Press(hotkey), task);        
    }

    public void onIntellitype(int command) {
        try {
            CommandButton commandButton = CommandButton.create(command);
            add(new Press(commandButton));
            add(new Release(commandButton));
        } catch (UnknownButtonException e) {
            e.printStackTrace();
        }
    }

    public void onHotKey(int id) {
        Hotkey hotkey = hotkeyList.get(id);
        add(new Press(hotkey));
        add(new Release(hotkey));
    }

    public void exit() throws DeviceExitException {
        super.exit();
        jit.removeHotKeyListener(this);
        jit.removeIntellitypeListener(this);
        jit.cleanUp();
    }
}
