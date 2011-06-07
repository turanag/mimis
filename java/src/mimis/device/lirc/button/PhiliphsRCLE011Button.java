package mimis.device.lirc.button;

import mimis.device.lirc.LircButton;

public enum PhiliphsRCLE011Button implements LircButton {
    POWER ("Standby"),
    RED ("Red"),
    GREEN ("Green"),
    YELLOW ("Yellow"),
    BLUE ("Blue"),
    TUNE ("Tune"),
    RADIO ("Radio"),
    SQUARE ("Square"),
    MENU ("Menu"),
    TEXT ("Text"),
    UP ("Up"),
    DOWN ("Down"),
    LEFT ("Left"),
    RIGHT ("Right"),
    VOLUME_UP ("Volume+"),
    VOLUME_DOWN ("Volume-"),
    MUTE ("Mute"),
    PROGRAM_UP ("Program+"),
    PROGRUM_DOWN ("Program-"),
    ONE ("1"),
    TWO ("2"),
    THREE ("3"),
    FOUR ("4"),
    FIVE ("5"),
    SIX ("6"),
    SEVEN ("7"),
    EIGHT ("8"),
    NINE ("9"),
    ZERO ("0"),
    CLOCK ("Clock"),
    OUT ("Out"),
    INFO ("i+"),
    SCREEN_UP ("screenup"),
    SCREEN_DOWN ("screendown"),
    QUESTION ("question");

    public static final String NAME = "Philips_RCLE011";

    protected String code;

    private PhiliphsRCLE011Button(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return NAME;
    }
}
