package pm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Worker implements Runnable {
    protected Log log = LogFactory.getLog(getClass());

    protected static final boolean THREAD = true;
    protected static final int SLEEP = 100;
    public static final int CHECK_ALIVE_INTERVAL = 1000;

    protected boolean running = false;
    protected boolean active = false;
    protected boolean connected;
    

    protected Object lock;
    
    public void start(boolean thread) {
        running = true;
        if (thread) {
            new Thread(this).start();
        } else {
            run();
        }
        activate();
    }

    public void start() {
        start(THREAD);
        monitorConnection();
        connected = false;
    }

    public void stop() {
        running = false;
        deactivate();
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

    public void activate() {
        if (!running) {
            start();
        }
        synchronized (this) {
            notifyAll();
        }
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public void deactivate(boolean stop) {
        deactivate();
        if (stop && running) {
            stop();
        }
    }

    public final void run() {
        while (running) {
            if (active()) {
                work();
            } else {
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
    
    public boolean connected() {
        return connected;
    }
    
    protected void monitorConnection() {
        new Thread() {
            protected long timestamp = System.currentTimeMillis();
            
            public void run() {
                while (super.isAlive()) {
                    if (timestamp + (2 * CHECK_ALIVE_INTERVAL) > System.currentTimeMillis()) {
                        timestamp = System.currentTimeMillis();
                        connected = true;
                        log.debug("Het gaat nog helemaal goed");
                    } else {
                        log.debug("Het interval is overschreden");
                        return;
                    }
                    try {
                        Thread.sleep(CHECK_ALIVE_INTERVAL);
                    } catch (InterruptedException e) {}
                }
                connected = false;
            }
        }.start();
    }
}
