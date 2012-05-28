package mimis.state;

import mimis.input.state.Hold;
import mimis.input.state.Press;
import mimis.input.state.State;
import mimis.input.state.sequence.Sequence;

public class Active {
    protected Sequence sequence;
    protected int step;

    public Active(Sequence sequence) {
        this.sequence = sequence;
        step = -1;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public boolean next(State state) {
        State next = sequence.get(++step);
        if (next == null) {
            return false;
        }
        boolean type = state instanceof Press && next instanceof Hold;
        return state.equals(next, type);
    }

    public boolean first() {
        return step == 0;
    }

    public boolean last() {
        return step == sequence.count() - 1;
    }
}
