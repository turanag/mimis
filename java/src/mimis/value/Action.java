package mimis.value;

import mimis.exception.task.action.ActionDeserializeException;

public enum Action {
    EXIT,
    FORWARD,
    MUTE,
    NEXT,
    PAUSE,
    PLAY,
    PREVIOUS,
    REPEAT,
    RESUME,
    REWIND,
    START,
    TEST,
    VOLUME_DOWN,
    VOLUME_UP,
    FULLSCREEN,
    TRAIN, STOP, SAVE, RECOGNIZE, LOAD, SHUFFLE, FADEOUT, QUIT, VISUALISER, LIKE, DISLIKE;

    public String serialze() {
        return name();
    }

    public static Action deserialise(String value) throws ActionDeserializeException {
        try {
            return Action.valueOf(value);
        } catch (NullPointerException e) {
            throw new ActionDeserializeException();
        }
    }
}
