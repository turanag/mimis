package pm.exception.macro;

import pm.exception.MacroException;

public class StateOrderException extends MacroException {
    protected static final long serialVersionUID = 1L;

    public StateOrderException(String message) {
        super(message);
    }
}
