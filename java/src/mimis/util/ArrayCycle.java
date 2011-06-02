package mimis.util;

import java.util.ArrayList;

public class ArrayCycle<E> extends ArrayList<E> {
    protected static final long serialVersionUID = 1L;

    protected int index = 0;
    //protected Object nonEmpty;

    public ArrayCycle(E... elementArray) {
        if (elementArray != null) {
        //nonEmpty = new Object();
            for (E element : elementArray) {
                add(element);
            }
        }
    }

    /*public boolean add(E element) {
        boolean result = super.add(element);
        synchronized (nonEmpty) {
            nonEmpty.notifyAll();
        }
        return result;
    }*/
    
    public E current() {
        /*while (index == 0) {
            synchronized (nonEmpty) {
                try {
                    nonEmpty.wait();
                } catch (InterruptedException e) {}
            }
        }*/
        return this.get(index);
    }

    public E previous() {
        if (--index < 0) {
            index = Math.max(0, size() - 1);
        }
        return get(index);
    }

    public E next() {
        if (++index >= size()) {
            index = 0;
        }
        return size() == 0 ? null : get(index);
    }

    public E reset() {
        return get(index = 0);
    }
}
