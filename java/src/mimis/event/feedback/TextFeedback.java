package mimis.event.feedback;

import mimis.event.Feedback;

public class TextFeedback extends Feedback {
    protected static final long serialVersionUID = 1L;

    protected String text;
    
    public TextFeedback(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
}
