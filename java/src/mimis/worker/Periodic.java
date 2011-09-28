package mimis.worker;

import java.util.Timer;
import java.util.TimerTask;

import mimis.Worker;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;

public class Periodic extends Worker {
    public static final int PERIOD = 500;

    protected TimerTask timerTask;
    protected Timer timer;
    protected int period;

    public Periodic() {
        this(PERIOD);
    }

    public Periodic(int period) {
        this.period = period;
        timerTask = new TimerTask() {
            public void run() {
                if (deactivate) {
                    cancel();
                }
                work();                
            }            
        };
        timer = new Timer();
    }

    protected void activate() throws ActivateException {
        timer.scheduleAtFixedRate(timerTask, 0, period);
        
    }

    protected void deactivate() throws DeactivateException {
        //timer.cancel();
    }

    protected void work() {
        log.debug("work!");
    }

    public void test() {
        Periodic periodic = new Periodic();
        periodic.start();
        sleep(1000);
        periodic.stop();
        sleep(1000);
        periodic.start();
        sleep(10000);
        System.exit(1);
        Worker worker = new Worker() {
            protected void work() {
                log.debug("work()");
                sleep();
            }            
        };
        worker.start();
        sleep(1000);
        worker.stop();
        sleep(1000);
        worker.start();
        worker.start();
    }

    public static void main(String[] args) {
        new Periodic().test();
    }

}
