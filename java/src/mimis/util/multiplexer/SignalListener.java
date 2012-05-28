package mimis.util.multiplexer;

import mimis.value.Signal;

public interface SignalListener<T> {
    public void add(Signal signal, T type);
}
