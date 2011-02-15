package pm.device.wiimote.gesture;

import org.wiigee.device.Device;
import org.wiigee.event.ButtonListener;
import org.wiigee.event.ButtonPressedEvent;
import org.wiigee.event.GestureListener;
import pm.device.wiimote.gesture.event.Close;
import pm.device.wiimote.gesture.event.Recognize;
import pm.device.wiimote.gesture.event.Train;

public class GestureDevice extends Device /*implements AccelerationListener */{
    public static final boolean AUTOFILTERING = true;
    //public static final boolean AUTOMOTION = true;
    
    public GestureDevice(boolean autofiltering/*, boolean automotion*/) {
        super(autofiltering);
        /*if (automotion) {
            addAccelerationListener(this);
        }*/
    }

    public GestureDevice() {
        this(AUTOFILTERING/*, AUTOMOTION*/);
    }

    public void add(GestureListener gestureListener) {
        addGestureListener(gestureListener);
    }

    public void add(double[] vector) {
        //System.out.printf("%f %f %f\n", vector[0], vector[1], vector[2]);
        fireAccelerationEvent(vector);
    }

    public void recognize() {
        fireButtonPressedEvent(new Recognize(this));
    }

    public void train() {
        fireButtonPressedEvent(new Train(this));
    }

    public void close() {
        fireButtonPressedEvent(new Close(this));
    }

    public void stop() {
        fireButtonReleasedEvent(0);
    }

    public void fireButtonPressedEvent(ButtonPressedEvent buttonPressedEvent) {
        for (ButtonListener bttnLstnr : buttonlistener) {
                bttnLstnr.buttonPressReceived(buttonPressedEvent);
        }
        if (buttonPressedEvent.isRecognitionInitEvent() || buttonPressedEvent.isTrainInitEvent()) {
            this.resetAccelerationFilters();
        }
    }

    /*public void accelerationReceived(AccelerationEvent event) {}

    public void motionStartReceived(MotionStartEvent event) {
        //System.out.println("Motion start!");
        recognize();
    }

    public void motionStopReceived(MotionStopEvent event) {
        //System.out.println("Motion stop!");
        stop();
    }*/
}
