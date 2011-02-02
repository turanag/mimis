package pm.device;

import java.util.ArrayList;

import pm.device.feature.Feature;


public abstract class Device {
    protected ArrayList<Feature> featureList;

    protected Device() {
        featureList = new ArrayList<Feature>();
    }

    public void addFeature(Feature feature) {
        if (!hasFeature(feature)) {
            if (this instanceof feature.getClass()) {
                
            }
            featureList.add(feature);
        }
    }

    public void removeFeature(Feature feature) {
        featureList.remove(feature);
    }

    public boolean hasFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
