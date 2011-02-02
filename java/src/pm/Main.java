package pm;

import java.util.ArrayList;

import pm.device.Device;
import pm.device.Features;
import pm.device.exampledevice.ExampleDevice;

public class Main {
    ArrayList<Device> deviceList;

    public Main() {
        deviceList = new ArrayList<Device>();
    }

    public void addDevice(Device device) {
        deviceList.add(device);
        if (device.hasFeature(Features.RESTART)) {
            device.start();
        }
    }

    public void start() {
       addDevice(new ExampleDevice());
    }

    public static void main(String[] args) {
        new Main().start();
    }
}