package wiiusej.wiiusejevents.wiiuseapievents;

public class BalanceBoardInsertedEvent extends WiiUseApiEvent {
    public BalanceBoardInsertedEvent(int id) {
        super(id, WIIUSE_BALANCE_BOARD_CTRL_INSERTED);
    }

    public String toString() {
        String out = "";
        /* Status */
        out += "/*********** BALANCE BOARD INSERTED EVENT : WIIMOTE   ID :"
                + super.getWiimoteId() + " ********/\n";
        return out;
    }
}
