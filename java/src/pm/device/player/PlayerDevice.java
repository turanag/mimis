package pm.device.player;

import javazoom.jlgui.player.amp.StandalonePlayer;
import pm.Device;
import pm.exception.device.DeviceExitException;

public class PlayerDevice extends Device {
    StandalonePlayer standalonePlayer;

    public void initialise() {
        standalonePlayer = new StandalonePlayer();
        standalonePlayer.loadUI();
        //standalonePlayer.loadJS();
        //standalonePlayer.loadPlaylist();
        //standalonePlayer.
        System.out.println("niets!");
    }

    public void exit() throws DeviceExitException {
        super.exit();
    }
}
