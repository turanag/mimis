package mimis.device.lirc;

import mimis.Button;

public interface LircButton extends Button {
    public static final String NAME = null;

    public String getCode();
    public String getName();
}
