package mimis.manager;

import java.util.Calendar;

import mimis.exception.worker.DeactivateException;
import mimis.worker.Worker;

public class Manager extends Worker {
    protected static final int INTERVAL = 1000;

    protected Worker[] workerArray;

    public Manager(Worker... workerArray) {
        this.workerArray = workerArray;
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        for (Worker worker : workerArray) {
            worker.stop();
        }
    }

    public void exit() {
        super.exit();
        for (Worker worker : workerArray) {
            worker.exit();
        }
    }

    public int count() {
        return workerArray.length;
    }

    protected void work() {
        long before = Calendar.getInstance().getTimeInMillis();
        for (Worker worker : workerArray) {
            worker.active();
        }
        long after = Calendar.getInstance().getTimeInMillis();
        int sleep = INTERVAL - (int) (after - before);
        sleep(sleep);
    }
}
