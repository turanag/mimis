package mimis.util.swing;

import javax.swing.ImageIcon;

import mimis.util.ArrayCycle;


public class ToggleButton extends CycleButton {
    protected static final long serialVersionUID = 1L;

    public ToggleButton(HoldButtonListener holdButtonListener, ImageIcon firstImageIcon, ImageIcon secondImageIcon) {
        super(holdButtonListener, new ArrayCycle<ImageIcon>(firstImageIcon, secondImageIcon));
    }

    public void toggle() {
        cycle();
    }
}
