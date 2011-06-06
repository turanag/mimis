package mimis;

import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Worker implements Runnable {
    protected Log log = LogFactory.getLog(getClass());

    protected static final boolean THREAD = true;
    protected static final int SLEEP = 100;

    protected boolean running = false;
    protected boolean active = false;

    public void start(boolean thread) {
        running = true;
        if (thread) {
            log.debug("Start thread");
            new Thread(this).start();
        } else {
            log.debug("Run directly");
            run();
        }
    }

    public void start() {
        start(THREAD);
    }

    public void stop() {
        log.trace("Stop");
        if (active()) {
            try {
                deactivate();
            } catch (DeactivateException e) {
                log.error(e);
            }
        }
        running = false;
        synchronized (this) {
            notifyAll();
        }
    }

    protected void sleep(int time) {
        try {
            if (time > 0) {
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            log.info(e);
        }
    }

    protected void sleep() {
        sleep(SLEEP);
    }

    public boolean active() {
        return active;
    }

    public void activate() throws ActivateException {
        activate(THREAD);
    }

    public void activate(boolean thread) {
        active = true;
        if (!running) {
            start(thread);
        }
        synchronized (this) {
            notifyAll();
        }
    }

    public void deactivate() throws DeactivateException {
        active = false;
    }

    public final void run() {
        while (running) {
            if (active()) {
                work();
            } else {
                try {
                    synchronized (this) {
                        log.trace("Wait");
                        wait();
                    }
                } catch (InterruptedException e) {
                    log.info(e);
                }
            }
        }
    }
    
    protected abstract void work();
}
