package pm.device.wiimote;

import java.util.ArrayList;
import java.util.HashMap;

import pm.exception.DeviceException;
import pm.exception.device.javainput.JavaInputDeviceSpecificException;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.GenericEvent;
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
    protected Wiimote[] wiimoteArray;
    protected HashMap<Integer, WiimoteDevice> wiimoteDeviceMap;

    WiimoteService() {
       wiimoteList = new ArrayList<Integer>();
       wiimoteArray = new Wiimote[0];
       wiimoteDeviceMap = new HashMap<Integer, WiimoteDevice>();
    }

    public void finish() {
        // Todo: methode bedenken om deze methode aangeroepen te krijgen, zonder de service in de main te hoeven registreren.
        if (wiimoteArray != null) {
            for (Wiimote wiimote : wiimoteArray) {
                wiimote.disconnect();
            }
        }
        definitiveShutdown();
    }

    public Wiimote getDevice(WiimoteDevice wiimoteDevice) throws DeviceException {
        Wiimote[] wiimoteArray = getWiimotes(1, false);
        for (Wiimote wiimote : wiimoteArray) {
            int id = wiimote.getId();
            if (!wiimoteList.contains(id)) {
                wiimote.addWiiMoteEventListeners(this);
                wiimoteList.add(id);
                wiimoteDeviceMap.put(id, wiimoteDevice); // Todo: controleren of dit nodig is. Ligt aan hoe uniek het id is na bijvoorbeeld een reconnect. Wellicht voldoet een arrayList ook.
                return wiimote;
            }
        }
        throw new JavaInputDeviceSpecificException();
    }

    public Wiimote getWiimote(GenericEvent event) {
        return wiimoteArray[event.getWiimoteId() - 1];
    }

    public WiimoteDevice getWiimoteDevice(GenericEvent event){
        return wiimoteDeviceMap.get(event.getWiimoteId());
    }

    public void onButtonsEvent(WiimoteButtonsEvent event) {
        
    }

    public void onStatusEvent(StatusEvent event) {
        if (event.isConnected()) {
            
        }
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
