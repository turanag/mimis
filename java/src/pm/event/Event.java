package pm.event;

import pm.device.feature.Feature;

public class Event {
    public Feature feature;
    
    public Event(Feature feature) {
        this.feature = feature;
    }
    
    public void Apply() {
           feature.apply();
    }
    
}
