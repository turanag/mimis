package pm.device;

import pm.Device;
import pm.Selector;

public class DeviceSelector extends Selector<Device> {
    protected static final long serialVersionUID = 1L;

    protected final static String TITLE = "MIMIS Device Selector";    

    public DeviceSelector(Device[] deviceArray) {
        super(deviceArray);
    }
}