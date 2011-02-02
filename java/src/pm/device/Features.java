package pm.device;

import pm.device.feature.Feature;
import pm.device.feature.Restart;

public enum Features {
    Restart ((Class<Restart>)Restart.class);

    Features(Class<Feature> feature) {
    }
}
