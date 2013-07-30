package mimis.util.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class HoldButton extends JButton implements MouseListener {
    protected static final long serialVersionUID = 1L;

    protected HoldButtonListener holdButtonListener;

    public HoldButton(HoldButtonListener holdButtonListener) {
        this.holdButtonListener = holdButtonListener;
    	addMouseListener(this);
    }

    public void mousePressed(MouseEvent event) {
    	if (event.getButton() == MouseEvent.BUTTON1) {
    	    holdButtonListener.buttonPressed(this);
    	}
    }

    public void mouseReleased(MouseEvent event) {
    	if (event.getButton() == MouseEvent.BUTTON1) {
    	    holdButtonListener.buttonReleased(this);
    	}
    }

	public void mouseClicked(MouseEvent event) {}
	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}
}
