package mimis.sequence;


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
        return next == null ? false : state.equals(next);
    }

    public boolean first() {
        return step == 0;
    }

    public boolean last() {
        return step == sequence.count() - 1;
    }
}
