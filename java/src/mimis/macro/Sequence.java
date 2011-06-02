package mimis.macro;

public class Sequence {
    protected State[] eventArray;

    public Sequence(State... eventArray) {
        this.eventArray = eventArray;
    }

    public int count() {
        return eventArray.length;
    }

    public State get(int i) {
        return eventArray.length > 0 ? eventArray[i] : null;
    }
}
