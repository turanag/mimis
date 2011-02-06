package pm.device.javainput.extreme3d;

public enum Extreme3DButton {
    ONE    ("Button 0"),
    TWO    ("Button 1"),
    THREE  ("Button 2"),
    FOUR   ("Button 3"),
    FIVE   ("Button 4"),
    SIX    ("Button 5"),
    SEVEN  ("Button 6"),
    EIGHT  ("Button 7"),
    NINE   ("Button 8"),
    TEN    ("Button 9"),
    ELEVEN ("Button 10"),
    TWELVE ("Button 11");

    /*TWO   (0x0001),
    ONE   (0x0002),
    B     (0x0004),
    A     (0x0008),
    MINUS (0x0010),
    HOME  (0x0080),
    LEFT  (0x0100),
    RIGHT (0x0200),
    DOWN  (0x0400),
    UP    (0x0800),
    PLUS  (0x1000),
    ALL   (0x1F9F);*/

    protected String code;

    Extreme3DButton(String code) {
        this.code = code;
    }

    /*int getCode() {
        return code;
    }*/

    static Extreme3DButton create(String name) throws Exception {
        try {
            return Extreme3DButton.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new Exception("Moet ik nou heeel boos worden?? " + name);
        }
    }
}