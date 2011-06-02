package mimis.device.wiimote.gesture;

import mimis.device.wiimote.gesture.event.Close;
import mimis.device.wiimote.gesture.event.Recognize;
import mimis.device.wiimote.gesture.event.Train;

import org.wiigee.device.Device;
import org.wiigee.event.ButtonListener;
import org.wiigee.event.ButtonPressedEvent;
import org.wiigee.event.GestureListener;
import wiiusej.values.GForce;

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

    public void add(GForce gforce) {
        add(new double[] {
            gforce.getX(),
            gforce.getY(),
            gforce.getY()});        
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
