package mimis.util.swing;

import javax.swing.ImageIcon;

import mimis.util.ArrayCycle;


public class CycleButton extends HoldButton {
    protected static final long serialVersionUID = 1L;

    protected ArrayCycle<ImageIcon> imageIconCycle;

    public CycleButton(HoldButtonListener holdButtonListener, ArrayCycle<ImageIcon> imageIconCycle) {
        super(holdButtonListener);
        this.imageIconCycle = imageIconCycle;
        cycle();
    }

    public void cycle() {
        setIcon(imageIconCycle.current());
        imageIconCycle.next();
    }
}
