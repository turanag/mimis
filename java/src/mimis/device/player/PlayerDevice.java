package mimis.device.player;

import mimis.Device;
import javazoom.jlgui.player.amp.StandalonePlayer;

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
