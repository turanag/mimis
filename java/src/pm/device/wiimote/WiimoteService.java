package pm.device.wiimote;

import java.util.ArrayList;
import java.util.HashMap;

import pm.exception.DeviceException;
import pm.exception.device.JavaInputDeviceNotFoundException;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

public class WiimoteService extends WiiUseApiManager implements WiimoteListener {
    protected ArrayList<Integer> wiimoteList;
    protected HashMap<Wiimote, WiimoteDevice> wiimoteMap;

    WiimoteService() {
       wiimoteList = new ArrayList<Integer>();
       wiimoteMap = new HashMap<Wiimote, WiimoteDevice>();
    }

    public Wiimote getDevice(WiimoteDevice wiimoteDevice) throws DeviceException {
        Wiimote[] wiimoteArray = getWiimotes(1, false);
        for (Wiimote wiimote : wiimoteArray) {
            int id = wiimote.getId();
            if (!wiimoteList.contains(id)) {
                wiimote.addWiiMoteEventListeners(this);
                wiimoteList.add(id);
                wiimoteMap.put(wiimote, wiimoteDevice);
                return wiimote;
            }
        }
        throw new JavaInputDeviceNotFoundException();
    }

    public void onButtonsEvent(WiimoteButtonsEvent event) {
        //evm.macroEvent(event, getWiimote(event));
    }

    public void onStatusEvent(StatusEvent event) {
        /*if (event.isConnected()) {
            evm.event(WiimoteEvent.CONNECT, getWiimote(event));
        }*/
    }

    public void onIrEvent(IREvent e) {}
    public void onMotionSensingEvent(MotionSensingEvent event) {}
    public void onExpansionEvent(ExpansionEvent event) {}
    public void onDisconnectionEvent(DisconnectionEvent event) {}
    public void onNunchukInsertedEvent(NunchukInsertedEvent event) {}
    public void onNunchukRemovedEvent(NunchukRemovedEvent event) {}
    public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent event) {}
    public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent event) {}
    public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent event) {}
    public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent event) {}
}
