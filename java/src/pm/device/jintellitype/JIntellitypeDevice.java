package pm.device.jintellitype;

import java.util.ArrayList;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.IntellitypeListener;
import com.melloware.jintellitype.JIntellitype;

import pm.Action;
import pm.Macro;
import pm.Target;
import pm.device.Device;
import pm.exception.EventException;
import pm.exception.MacroException;
import pm.macro.event.Press;
import pm.macro.event.Release;

public class JIntellitypeDevice extends Device implements HotkeyListener, IntellitypeListener {
    protected ArrayList<Hotkey> hotkeyList;
    protected JIntellitype jit;

    public JIntellitypeDevice() {
        hotkeyList = new ArrayList<Hotkey>();
        jit = JIntellitype.getInstance();
        Hotkey.initialise(hotkeyList, jit);
    }

    public void start() {
        super.start();
        jit.addHotKeyListener(this);
        jit.addIntellitypeListener(this);
        try {
            add(
                new Macro(
                    new Hotkey('r'),
                    new Hotkey('i'),
                    new Hotkey('k')),
                Action.EXIT.setTarget(Target.MAIN));
            add(
                new Press(CommandButton.VOLUME_UP),
                Action.EXIT.setTarget(Target.MAIN));
        } catch (MacroException e) {
            e.printStackTrace();
        }
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
