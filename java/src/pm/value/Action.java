package pm.value;

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
    
    public static Action deserialize(String value) {  
        if (value != null) {  
            for (Action action : values()) {  
                if (action.name().equals(value)) {  
                    return action;  
                }  
            }  
        }
        return null; 
    }
}
