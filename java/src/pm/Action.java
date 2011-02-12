package pm;

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

    private Action(String action) {
        this.action = action;
    }
}
