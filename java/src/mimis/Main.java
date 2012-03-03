package mimis;

import javax.swing.UIManager;

import mimis.application.cmd.windows.gomplayer.GomPlayerApplication;
import mimis.application.cmd.windows.photoviewer.PhotoViewerApplication;
import mimis.application.cmd.windows.winamp.WinampApplication;
import mimis.application.cmd.windows.wmp.WMPApplication;
import mimis.application.itunes.iTunesApplication;
import mimis.application.lirc.ipod.iPodApplication;
import mimis.application.mpc.MPCApplication;
import mimis.application.vlc.VLCApplication;
import mimis.device.javainput.extreme3d.Extreme3DDevice;
import mimis.device.javainput.rumblepad.RumblepadDevice;
import mimis.device.jintellitype.JIntellitypeDevice;
import mimis.device.lirc.LircDevice;
import mimis.device.network.NetworkDevice;
import mimis.device.panel.PanelDevice;
import mimis.device.wiimote.WiimoteDevice;
import mimis.exception.worker.ActivateException;
import mimis.exception.worker.DeactivateException;
import mimis.input.Task;
import mimis.manager.ButtonManager;
import mimis.manager.CurrentButtonManager;
import mimis.value.Action;

public class Main extends Mimis {
    protected CurrentButtonManager applicationManager;
    protected ButtonManager deviceManager;
    protected Gui gui;

    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        
    }

    public Main() {
        super(
            new WinampApplication(), new GomPlayerApplication(), new WMPApplication(), new MPCApplication(), // WindowsApplication
            new VLCApplication(),         // CMDApplication
            new iPodApplication(),        // LircApplication
            new PhotoViewerApplication(), // RobotApplication
            new iTunesApplication());     // Component

        /* Create gui from application and device managers */
        applicationManager = new CurrentButtonManager(router, componentCycle, "Applications", currentArray);
        deviceManager = new ButtonManager("Devices", initialize(false,
            new Extreme3DDevice(), new RumblepadDevice(), // JavaInputDevice
            new JIntellitypeDevice(), new PanelDevice(), new LircDevice(), new WiimoteDevice(), new NetworkDevice())); // Component
        gui = new Gui(this, applicationManager, deviceManager);
        manager.add(initialize(false, gui));
    }

    public void activate() throws ActivateException {
        super.activate();
        listen(Task.class);

        /* Start managers */
        applicationManager.start();
        deviceManager.start();

        /* Force display of currenct component when gui started */
        gui.start();
        while (!gui.active());
        end(Action.CURRENT);
    }

    protected void deactivate() throws DeactivateException {
        super.deactivate();

        log.debug("Stop managers");
        applicationManager.stop();
        deviceManager.stop();
    }

    public void exit() {
        super.exit();

        log.debug("Exit managers");
        applicationManager.exit();
        deviceManager.exit();
    }

    public void end(Action action) {
        super.end(action);
        switch (action) {
            case CURRENT:
            case NEXT:
            case PREVIOUS:
                applicationManager.currentChanged();
                break;
        }
    }

    public static void main(String[] args) {
        new Main().start(false);
    }
}
