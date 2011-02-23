package pm;

public abstract class Listener implements Runnable {
    protected static final int SLEEP = 100;

    protected boolean run;

    public void start() {
        run = true;
        new Thread(this).start();
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
