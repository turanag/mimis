package pm.device.feature;

public interface Restart extends Feature {
    public static final String feature = "RESTART";

    public void stop();
    public void restart();
}