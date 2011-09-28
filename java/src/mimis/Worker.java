package mimis;

import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Worker implements Runnable {
    protected Log log = LogFactory.getLog(getClass());

    protected static final boolean THREAD = true;
    protected static final int SLEEP = 100;

    protected boolean run = false;
    protected boolean active = false;
    protected boolean activate = false;
    protected boolean deactivate = false;

    public final void start(boolean thread) {
        if (!active) {
            activate = true;
        }
        if (!run) {
            run = true;
            if (thread) {
                log.debug("Start thread");
                new Thread(this, getClass().getName()).start();
            } else {
                log.debug("Run directly");
                run();
            }
        } else {
            notifyAll();
        }
    }

    public synchronized final void start() {
        start(THREAD);
    }

    public synchronized final void stop() {
        if (active) {
            deactivate = true;
        }
        notifyAll();
    }

    public void exit() {
        stop();
        run = false;
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

    protected void activate() throws ActivateException {
        active = true;
    }

    protected void deactivate() throws DeactivateException {
        active = false;
    }

    public final void run() {
        while (run || deactivate) {
            //log.debug("run() run=" + run + ", active=" + active + ", activate=" + activate + ", deactivate=" + deactivate);
            if (activate && !active) {
                try {
                    activate();
                } catch (ActivateException e) {
                    log.error(e);
                } finally {
                    activate = false;
                }
            } else if (deactivate && active) {
                try {
                   deactivate();
                } catch (DeactivateException e) {
                    log.error(e);
                } finally {
                    deactivate = false;
                }
            }
            if (active) {
                work();
            } else if (run) {
                try {
                    synchronized (this) {
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
