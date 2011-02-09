package pm.device.javainput;

import pm.Button;

public class JavaInputButton implements Button {
    protected int code;

    public JavaInputButton(int button) {
        code = button - 1;
    }

    public String toString() {
        return String.valueOf(code + 1);
    }
}
