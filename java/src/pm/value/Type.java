package pm.value;

public enum Type {
    UP      (0x0101), // WM_KEYUP
    DOWN    (0x0100), // WM_KEYDOWN
    SYSUP   (0x0105), // WM_SYSKEYUP
    SYSDOWN (0x0104); // WM_SYSKEYDOWN

    protected int code;

    private Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
