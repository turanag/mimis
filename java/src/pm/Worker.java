package pm;

public abstract class Worker implements Runnable {
    protected static final boolean THREAD = true;
    protected static final int SLEEP = 100;

    protected boolean run;

    public void start(boolean thread) {
        run = true;
        if (thread) {
            new Thread(this).start();
        } else {
            run();
        }
    }

    public void start() {
        start(THREAD);
    }

    public void stop() {
        run = false;
    }

    protected void sleep(int time) {
        try {
            if (time > 0) {
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {}
    }

    protected void sleep() {
        sleep(SLEEP);
    }
}
