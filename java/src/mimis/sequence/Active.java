package mimis.sequence;


public class Active {
    protected Sequence sequence;
    protected int step;
    //protected long start;
    //protected long duration;

    public Active(Sequence sequence) {
        this.sequence = sequence;
        step = -1;
        //start = System.nanoTime();
    }

    public Sequence getSequence() {
        return sequence;
    }

    /*public long getDuration() {
        return duration;
    }*/

    public boolean next(State state) {
        State next = sequence.get(++step);
        return next == null ? false : state.equals(next);
    }
    
    public boolean first() {
        return step == 0;
    }

    public boolean last() {
        return step == sequence.count() - 1;
        /*boolean last = step == sequence.count() - 1;
        if (last) {
            duration = System.nanoTime() - start;
        }
        return last;*/
    }
}
