package mimis.device.jintellitype;

import java.util.ArrayList;

import mimis.exception.button.UnknownButtonException;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.input.state.Press;
import mimis.input.state.Release;
import mimis.worker.Component;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.IntellitypeListener;
import com.melloware.jintellitype.JIntellitype;

public class JIntellitypeDevice extends Component implements HotkeyListener, IntellitypeListener {
    protected static final String TITLE = "Keyboard";

    protected JIntellitypeTaskMapCycle taskMapCycle;
    protected ArrayList<Hotkey> hotkeyList;
    protected JIntellitype jit;

    public JIntellitypeDevice() {
        super(TITLE);
        hotkeyList = new ArrayList<Hotkey>();
        jit = JIntellitype.getInstance();
        Hotkey.initialise(hotkeyList, jit);
        taskMapCycle = new JIntellitypeTaskMapCycle();
    }

    protected void activate() throws ActivateException {
        super.activate();
        jit.addHotKeyListener(this);
        jit.addIntellitypeListener(this);
        add(taskMapCycle.mimis);
        add(taskMapCycle.player);
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

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        jit.removeHotKeyListener(this);
        jit.removeIntellitypeListener(this);
    }

    public void exit() {
        super.exit();
        jit.cleanUp();
    }
}
