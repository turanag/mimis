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
        return event.getClass().equals(getClass()) && event.getButton().equals(button); // Todo: controleren of equals goed werkt bij buttons van verschillende typen
    }
}
