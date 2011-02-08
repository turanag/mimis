package pm.util;

import java.util.ArrayList;

public class ArrayCycle<T> extends ArrayList<T> {
    protected static final long serialVersionUID = 1L;

    protected int index = 0;

    public T current() {
        return this.get(index);
    }

    public T previous() {
        if (--index < 0) {
            index = Math.max(0, size() - 1);
        }
        return get(index);
    }

    public T next() {
        if (++index >= size()) {
            index = 0;
        }
        return get(index);
    }

    public T reset() {
        return get(index = 0);
    }
}
