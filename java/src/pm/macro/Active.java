package pm.macro;

import pm.Macro;

public class Active {
    protected Macro macro;
    protected int step;

    public Active(Macro macro) {
        this.macro = macro;
        step = -1;
    }

    public Macro getMacro() {
        return macro;
    }

    public boolean next(Event event) {
        Event next = macro.get(++step);
        return next == null ? false : event.equals(next);
    }

    public boolean last() {
        return step == macro.count() - 1;
    }
}
