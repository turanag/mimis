package wiiusej.wiiusejevents.wiiuseapievents;

import wiiusej.wiiusejevents.wiiuseapievents.WiiUseApiEvent;

public class BalanceBoardRemovedEvent extends WiiUseApiEvent {
    public BalanceBoardRemovedEvent(int id) {
        super(id, WIIUSE_BALANCE_BOARD_CTRL_REMOVED);
    }

    public String toString() {
        String out = "";
        /* Status */
        out += "/*********** BALANCE BOARD INSERTED EVENT : WIIMOTE   ID :"
                + super.getWiimoteId() + " ********/\n";
        return out;
    }
}
