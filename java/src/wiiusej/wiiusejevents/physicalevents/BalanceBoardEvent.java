package wiiusej.wiiusejevents.physicalevents;

public class BalanceBoardEvent extends ExpansionEvent {
    protected JoystickEvent balanceBoardJoystickEvent;

    public BalanceBoardEvent(int id, float topRight, float bottomRight,
            float bottomLeft, float topLeft) {
        super(id);
        System.out.println(String.format("%f %f %f %f", topRight, bottomRight, bottomLeft, topLeft));
        /*balanceBoardJoystickEvent = new JoystickEvent(id, angle,
                magnitude, max1, max2, min1, min2, center1, center2);*/
    }

    public boolean isThereBalanceBoardJoystickEvent() {
        return balanceBoardJoystickEvent != null;
    }

    public JoystickEvent getBalanceBoardJoystickEvent() {
        return balanceBoardJoystickEvent;
    }

    public String toString() {
        String out = "";
        /* Status */
        out += "/*********** Balance Board EVENT : WIIMOTE   ID :" + getWiimoteId()
                + " ********/\n";
        out += balanceBoardJoystickEvent;
        return out;
    }

}