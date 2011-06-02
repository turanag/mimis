package mimis.device.jintellitype;

import mimis.Button;

import com.melloware.jintellitype.JIntellitype;


public class Modifier implements Button {
    public static final int
        ALT = JIntellitype.MOD_ALT,
        CTRL = JIntellitype.MOD_CONTROL,
        SHIFT = JIntellitype.MOD_SHIFT,
        WIN = JIntellitype.MOD_WIN;

    protected int code;

    protected Modifier(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
