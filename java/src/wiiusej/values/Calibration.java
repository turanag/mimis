package wiiusej.values;

public class Calibration {
    protected RawAcceleration zeroAcceleration;
    protected RawAcceleration gAcceleration;
    protected RawAcceleration differenceAcceleration;
    
    public Calibration(RawAcceleration zeroAcceleration, RawAcceleration gAcceleration) {
        this.zeroAcceleration = zeroAcceleration;
        this.gAcceleration = gAcceleration;
        differenceAcceleration = new RawAcceleration(
            (short) (zeroAcceleration.getX() - gAcceleration.getX()),
            (short) (zeroAcceleration.getY() - gAcceleration.getY()),
            (short) (zeroAcceleration.getZ() - gAcceleration.getZ()));
        System.out.println(zeroAcceleration);
        System.out.println(gAcceleration);
        System.out.println(differenceAcceleration);
        System.out.println("<");
        //System.exit(0);
    }

    public RawAcceleration getZeroAcceleration() {
        return zeroAcceleration;
    }

    public RawAcceleration getGAcceleration() {
        return gAcceleration;
    }

    public Acceleration getAcceleration(RawAcceleration rawAcceleration) {
        return new Acceleration(
            (rawAcceleration.getX() - zeroAcceleration.getX()),// / (double) gAcceleration.getX(),
            (rawAcceleration.getY() - zeroAcceleration.getY()),// / (double) gAcceleration.getY(),
            (rawAcceleration.getZ() - zeroAcceleration.getZ()));// / (double) gAcceleration.getZ());
    }
}
