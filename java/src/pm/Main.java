package pm;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import pm.applicatie.voorbeeld.Voorbeeld;
import pm.application.Application;
import pm.device.Device;
import pm.device.example.Example;
import pm.event.Event;

public class Main {
    ArrayList<Application> applicationList;
    ArrayList<Device> deviceList;
    Queue<Event> eventQueue;

    public Main() {
        applicationList = new ArrayList<Application>();
        deviceList = new ArrayList<Device>();
        eventQueue = new ConcurrentLinkedQueue<Event>();
    }

    public void addApplication(Application application) {
        applicationList.add(application);
    }

    public boolean removeApplication(Application application) {
        return applicationList.remove(application);
    }

    public void addDevice(Device device) {
        deviceList.add(device);
    }

    public boolean removeDevie(Device device) {
        return deviceList.remove(device);
    }

    public void start() {
        addDevice(new Example(eventQueue));
        addApplication(new Voorbeeld());
    }

    public static void main(String[] args) {
        new Main().start();
    }
}