package mimis.sequence;

import mimis.Button;

public abstract class State {
    protected Button button;

    public State(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public boolean equals(State state) {
        return state.getClass().equals(getClass()) && state.getButton().equals(button);
    }
}
