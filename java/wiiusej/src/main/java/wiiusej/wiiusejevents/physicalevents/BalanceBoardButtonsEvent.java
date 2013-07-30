package wiiusej.wiiusejevents.physicalevents;

public class BalanceBoardButtonsEvent extends ButtonsEvent {
    protected static short BALANCE_BOARD_BUTTON = 0x0001;

    public BalanceBoardButtonsEvent(int id, short buttonsJustPressed,
            short buttonsJustReleased, short buttonsHeld) {
        super(id, buttonsJustPressed, buttonsJustReleased, buttonsHeld);
    }

}
