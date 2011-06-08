package mimis.manager;

import mimis.exception.worker.DeactivateException;

public interface Exitable {
    public void stop() throws DeactivateException;
}
