package mimis.util.multiplexer;

import mimis.value.Signal;

public interface SignalListener {
    public void add(Signal signal, Object object);
}
