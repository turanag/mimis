package pm.device.jintellitype;

import com.melloware.jintellitype.JIntellitype;

import pm.Button;

public class HotkeyButton implements Button {
    public static final int
        ALT = JIntellitype.MOD_ALT,
        CTRL = JIntellitype.MOD_CONTROL,
        SHIFT = JIntellitype.MOD_SHIFT,
        WIN = JIntellitype.MOD_WIN;

    protected int code;

    protected HotkeyButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
