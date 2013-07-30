package mimis.manager;

import java.util.ArrayList;
import java.util.Arrays;

import mimis.exception.worker.DeactivateException;
import mimis.worker.IntervalWorker;
import mimis.worker.Worker;

public class Manager extends IntervalWorker {
    protected static final int INTERVAL = 1000;

    protected ArrayList<Worker> workerList;

    public Manager(Worker... workerArray) {
        workerList = new ArrayList<Worker>();
        add(workerArray);
    }

    public void add(Worker... workerArray) {
        workerList.addAll(Arrays.asList(workerArray));
    }

    public void remove(Worker... workerArray) {
        workerList.removeAll(Arrays.asList(workerArray));
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();
        for (Worker worker : workerList) {
            worker.stop();
        }
    }

    public void exit() {
        super.exit();
        for (Worker worker : workerList) {
            worker.exit();
        }
    }

    public int count() {
        return workerList.size();
    }

    protected void work() {
        for (Worker worker : workerList) {
            worker.active();
        }
    }
}
