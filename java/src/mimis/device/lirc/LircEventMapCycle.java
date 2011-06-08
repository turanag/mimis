package mimis.device.lirc;

import mimis.device.EventMapCycle;
import mimis.device.lirc.remote.DenonRC176EventMap;
import mimis.device.lirc.remote.PhiliphsRCLE011EventMap;
import mimis.device.lirc.remote.SamsungBN5901015AEventMap;
import mimis.sequence.EventMap;

public class LircEventMapCycle extends EventMapCycle {
    protected static final long serialVersionUID = 1L;

    public EventMap denonRC176, philiphsRCLE011, samsungBN5901015A;
    public LircEventMapCycle() {      
        add(denonRC176 = new DenonRC176EventMap());
        add(philiphsRCLE011 = new PhiliphsRCLE011EventMap());
        add(samsungBN5901015A = new SamsungBN5901015AEventMap());
        /*
        player = new EventMap();
        player.add(PanelButton.PREVIOUS, new Task(Target.APPLICATION, Action.PREVIOUS));
        player.add(PanelButton.REWIND, new Task(Target.APPLICATION, Action.REWIND));
        player.add(PanelButton.STOP, new Task(Target.APPLICATION, Action.STOP));
        player.add(PanelButton.PAUSE, new Task(Target.APPLICATION, Action.PAUSE));
        player.add(PanelButton.PLAY, new Task(Target.APPLICATION, Action.PLAY));
        player.add(PanelButton.FORWARD, new Task(Target.APPLICATION, Action.FORWARD));
        player.add(PanelButton.NEXT, new Task(Target.APPLICATION, Action.NEXT));
        player.add(PanelButton.VOLUME_DOWN, new Task(Target.APPLICATION, Action.VOLUME_DOWN));
        player.add(PanelButton.MUTE, new Task(Target.APPLICATION, Action.MUTE));
        player.add(PanelButton.VOLUME_UP, new Task(Target.APPLICATION, Action.VOLUME_UP));
        player.add(PanelButton.REPEAT, new Task(Target.APPLICATION, Action.REPEAT));
        player.add(PanelButton.SHUFFLE, new Task(Target.APPLICATION, Action.SHUFFLE));
        add(player);*/
    }
}
