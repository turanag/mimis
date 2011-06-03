package mimis.device.jintellitype;

import java.util.ArrayList;

import mimis.Device;
import mimis.event.Task;
import mimis.exception.button.UnknownButtonException;
import mimis.macro.state.Press;
import mimis.macro.state.Release;
import mimis.value.Action;
import mimis.value.Key;
import mimis.value.Target;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.IntellitypeListener;
import com.melloware.jintellitype.JIntellitype;


public class JIntellitypeDevice extends Device implements HotkeyListener, IntellitypeListener {
    protected static final String TITLE = "JIntellitype";

    protected ArrayList<Hotkey> hotkeyList;
    protected JIntellitype jit;

    public JIntellitypeDevice() {
        super(TITLE);
        hotkeyList = new ArrayList<Hotkey>();
        jit = JIntellitype.getInstance();
        Hotkey.initialise(hotkeyList, jit);
    }

    public void start() {
        super.start();
        jit.addHotKeyListener(this);
        jit.addIntellitypeListener(this);
        add(
            new Hotkey(Key.PRIOR),
            new Task(Target.MIMIS, Action.PREVIOUS));
        add(
            new Hotkey(Key.NEXT),
            new Task(Target.MIMIS, Action.NEXT));
        add(
            new Press(CommandButton.VOLUME_DOWN),
            new Task(Target.APPLICATIONS, Action.VOLUME_DOWN));
        add(
            new Press(CommandButton.VOLUME_UP),
            new Task(Target.APPLICATIONS, Action.VOLUME_UP));
        add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 'x'),
            new Task(Target.MIMIS, Action.EXIT));
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
        if (active) {
            try {
            CommandButton commandButton = CommandButton.create(command);
            add(new Press(commandButton));
            add(new Release(commandButton));
            } catch (UnknownButtonException e) {
                log.error(e);
            }
        }
    }

    public void onHotKey(int id) {
        if (active) {
            Hotkey hotkey = hotkeyList.get(id);
            add(new Press(hotkey));
            add(new Release(hotkey));
        }
    }

    public void stop() {
        super.stop();
        jit.removeHotKeyListener(this);
        jit.removeIntellitypeListener(this);
        jit.cleanUp();
    }
}
