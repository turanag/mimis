package mimis.feedback;

import mimis.event.Feedback;

public class TextFeedback extends Feedback {
    protected String text;
    
    public TextFeedback(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
}
