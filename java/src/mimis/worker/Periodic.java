package mimis.worker;

import mimis.Worker;

public class Periodic extends Worker {

    public Periodic() {

    }

    protected void work() {
    }

    public void test() {
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
