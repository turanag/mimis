package mimis.input;


public class Feedback implements Input {
    protected static final long serialVersionUID = 1L;

    protected String text;

    public Feedback(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}