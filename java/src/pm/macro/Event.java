package pm.macro;

import pm.Button;

public abstract class Event {
    protected Button button;

    public Event(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public boolean equals(Event event) {
        System.out.println(getClass() + " " + event.getClass());
        System.out.println(getButton() + " " + event.getButton());
        System.out.println(getButton().getClass() + " " + event.getButton().getClass());
        System.out.println(event.getButton().equals(button));
        return event.getClass().equals(getClass()) && event.getButton().equals(button);
    }
}
