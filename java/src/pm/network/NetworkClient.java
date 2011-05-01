package pm.network;

import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pm.Device;
import pm.client.LanTextClient;
import pm.device.gui.GUIDevice;
import pm.device.javainput.extreme3d.Extreme3DDevice;
import pm.device.javainput.rumblepad.RumblepadDevice;
import pm.device.jintellitype.JIntellitypeDevice;
import pm.device.panel.PanelDevice;
import pm.device.player.PlayerDevice;
import pm.device.text.TextDevice;
import pm.device.text.lan.LanTextDevice;
import pm.device.wiimote.WiimoteDevice;
import pm.event.EventListener;
import pm.event.EventManager;
import pm.exception.device.DeviceExitException;
import pm.exception.device.DeviceInitialiseException;
import pm.macro.Active;
import pm.value.Action;

public class NetworkClient extends EventListener {
    protected Log log = LogFactory.getLog(NetworkClient.class);

    protected ArrayList<Device> deviceList;
    protected MessageSender messageSender;

    public NetworkClient() {
        super();
        deviceList = new ArrayList<Device>();
        EventManager.initialise(null);
        EventManager.add(this);
    }

    public void initialise() throws DeviceInitialiseException {
        //add(new JIntellitypeDevice());
        //add(new PlayerDevice());
        //add(new RumblepadDevice());
        //add(new WiimoteDevice());
        //add(new GUIDevice());
        //add(new TextDevice());
        //add(new LanTextDevice());
        //add(new Extreme3DDevice());
        add(new PanelDevice());
        startDevices();
        messageSender = new MessageSender("192.168.1.101", 1234);
    }

    protected void startDevices() {
        ArrayList<Device> removeList = new ArrayList<Device>();
        for (Device device : deviceList) {
            try {
                device.initialise();
                device.start();
                log.info("Device started: " + device);
            } catch (DeviceInitialiseException e) {
                removeList.add(device);
            }
        }
        for (Device device : removeList) {
            remove(device);
        }        
    }

    public void exit() {
        System.out.println("Exit devices...");
        for (Device device : deviceList) {
            try {
                device.exit();
            } catch (DeviceExitException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Exit main...");
        stop();
    }

    protected void add(Action action) {
        System.out.println("NetworkClient: " + action);
        String message = action.serialze();
        messageSender.setMessage(message);
        messageSender.notify();
    }

    /* Add / remove methods */
    protected void add(Device device) {
        EventManager.add(device);
        deviceList.add(device);
    }

    protected void remove(Device device) {
        EventManager.remove(device);
        deviceList.remove(device);
    }

    public static void main(String[] args) {
        try {
            NetworkClient networkClient = new NetworkClient();
            networkClient.initialise();
            networkClient.start(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected class MessageSender {
        protected Socket socket;
        protected Scanner input;
        protected PrintStream output;

        protected String message;

        public MessageSender(String host, int port) {
            try {
                socket = new Socket(host, port);
                input = new Scanner(System.in);
                output = new PrintStream(socket.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void setMessage(String message) {
            this.message = message; 
        }

        protected void start() {
            while (true) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                output.println(message);
            }
        }
    }    
}
