package wiiusej.values;

public class Calibration {
    protected RawAcceleration zeroAcceleration;
    protected RawAcceleration gAcceleration;
    protected RawAcceleration differenceAcceleration;

    public Calibration(RawAcceleration zeroAcceleration, RawAcceleration gAcceleration) {
        this.zeroAcceleration = zeroAcceleration;
        this.gAcceleration = gAcceleration;
        differenceAcceleration = new RawAcceleration(
            (short) (gAcceleration.getX() - zeroAcceleration.getX()),
            (short) (gAcceleration.getY() - zeroAcceleration.getY()),
            (short) (gAcceleration.getZ() - zeroAcceleration.getZ()));
    }

    public RawAcceleration getZeroAcceleration() {
        return zeroAcceleration;
    }

    public RawAcceleration getGAcceleration() {
        return gAcceleration;
    }

    public Acceleration getAcceleration(RawAcceleration rawAcceleration) {
        return new Acceleration(
            (rawAcceleration.getX() - zeroAcceleration.getX()) / (double) differenceAcceleration.getX(),
            (rawAcceleration.getY() - zeroAcceleration.getY()) / (double) differenceAcceleration.getY(),
            (rawAcceleration.getZ() - zeroAcceleration.getZ()) / (double) differenceAcceleration.getZ());
    }
}
