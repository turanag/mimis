package pm;

import pm.exception.action.TargetNotSetException;

public enum Action {
    START ("start"),
    TEST ("test"),
    EXIT ("exit"),
    PLAY ("play"),
    PAUSE ("pause"),
    RESUME ("resume"),
    NEXT ("next"),
    PREVIOUS ("previous"),
    FORWARD ("forward"),
    REWIND ("rewind"),
    MUTE ("mute"),
    VOLUME_UP ("volumeUp"),
    VOLUME_DOWN ("volumeDown");
    
    
    protected String action;
    protected Target target;

    Action(String action) {
        this.action = action;
    }

    public Action setTarget(Target target) {
        this.target = target;
        return this;
    }

    public Target getTarget() throws TargetNotSetException {
        if (target == null) {
            throw new TargetNotSetException();
        }
        return target;
    }
}
