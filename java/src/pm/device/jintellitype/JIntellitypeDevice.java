package pm.device.jintellitype;

import java.util.ArrayList;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.IntellitypeListener;
import com.melloware.jintellitype.JIntellitype;

import pm.Action;
import pm.Device;
import pm.Target;
import pm.Task;
import pm.application.windows.Key;
import pm.exception.EventException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.event.Press;
import pm.macro.event.Release;
import pm.task.Continuous;

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
            new Task(Action.PREVIOUS, Target.MAIN));
        add(
            new Hotkey(Key.NEXT),
            new Task(Action.NEXT, Target.MAIN));
        add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 'q'),
            new Task(Action.PLAY, Target.APPLICATIONS));
        add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 'x'),
            new Task(Action.EXIT, Target.MAIN));
        add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 't'),
            new Task(Action.TEST, Target.MAIN));
        add(
            new Hotkey(Modifier.CTRL | Modifier.WIN, 'r'),
            new Hotkey(Modifier.CTRL | Modifier.WIN, 's'),
            new Continuous(Action.REPEAT, Target.APPLICATIONS, 500));
    }

    public void onIntellitype(int command) {
        CommandButton commandButton;
        try {
            commandButton = CommandButton.create(command);
            System.out.println(commandButton);
            add(new Press(commandButton));
            add(new Release(commandButton));
        } catch (EventException e) {
            e.printStackTrace(); // Todo: deze exception verder omhoog gooien
        }        
    }

    public void onHotKey(int id) {
        Hotkey hotkey = hotkeyList.get(id);
        add(hotkeyList.get(id));
        add(new Release(hotkey.getButton()));
    }

    public void exit() {
        jit.removeHotKeyListener(this);
        jit.removeIntellitypeListener(this);
        jit.cleanUp();
    }
}
