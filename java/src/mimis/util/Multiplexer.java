package mimis.util;

import mimis.Worker;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.util.multiplexer.SignalListener;
import mimis.value.Signal;

public class Multiplexer extends Worker {
    public static final int TIMEOUT = 150;

    protected int threshold;
    protected Object object;
    protected SignalListener signalListener;
    protected boolean end;

    public Multiplexer(SignalListener signalListener) {
        this(signalListener, TIMEOUT);
    }

    public Multiplexer(SignalListener signalListener, int treshold) {
        this.signalListener = signalListener;
    }

    public void add(Object object) {
        if (this.object == null) {
            signalListener.add(Signal.BEGIN, object);
            this.object = object;
            end = true;
            try {
                activate();
            } catch (ActivateException e) {
                log.error(e);
            }
        } else if (this.object.equals(object)) {
            end = false;
            synchronized (this) {
                notifyAll();            
            }
        } else {
            end = true;
            synchronized (this) {
                notifyAll();            
            }
            add(object);
        }
    }

    protected void work() {
        log.debug("Multiplexer work");
        try {
            synchronized (this) {
                wait(TIMEOUT);
            }
        } catch (InterruptedException e) {}
        if (end) {
            signalListener.add(Signal.END, object);
            object = null;
            try {
                deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        }
        end = !end;
    }
}
