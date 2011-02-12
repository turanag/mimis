package pm.device.gui;

import pm.device.Device;

public class GUIDevice extends Device {
    
    protected GUIDeviceUI gui;
    
    public GUIDevice() {
        gui = new GUIDeviceUI();
    }
}
