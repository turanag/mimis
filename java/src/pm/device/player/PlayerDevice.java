package pm.device.player;

import javazoom.jlgui.player.amp.StandalonePlayer;
import pm.Device;

public class PlayerDevice extends Device {
    protected static final String TITLE = "Player";
    
    StandalonePlayer standalonePlayer;
    
    public PlayerDevice() {
        super(TITLE);
    }

    public void initialise() {
        standalonePlayer = new StandalonePlayer();
        standalonePlayer.loadUI();
        //standalonePlayer.loadJS();
        //standalonePlayer.loadPlaylist();
        //standalonePlayer.
        System.out.println("niets!");
    }
}
