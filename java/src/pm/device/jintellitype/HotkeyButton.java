package pm.device.jintellitype;

import com.melloware.jintellitype.JIntellitype;

import pm.Button;

public class HotkeyButton implements Button {
    public static final int
        MOD_ALT = JIntellitype.MOD_ALT,
        MOD_CTRL = JIntellitype.MOD_CONTROL,
        MOD_SHIFT = JIntellitype.MOD_SHIFT,
        MOD_WIN = JIntellitype.MOD_WIN;

    protected int code;

    public HotkeyButton(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
