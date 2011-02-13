package pm.macro;

import pm.macro.event.Sequence;

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

    public boolean next(Event event) {
        Event next = sequence.get(++step);
        return next == null ? false : event.equals(next);
    }

    public boolean last() {
        return step == sequence.count() - 1;
    }
}
