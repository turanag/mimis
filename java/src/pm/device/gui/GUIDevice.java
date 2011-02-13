package pm.device.gui;

import pm.Device;

public class GUIDevice extends Device {    
    protected GUIDeviceUI gui;
    
    public GUIDevice() {
        gui = new GUIDeviceUI();
    }
}
