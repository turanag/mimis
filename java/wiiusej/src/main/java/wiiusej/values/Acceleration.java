package wiiusej.values;

public class Acceleration {
    protected double x;
    protected double y;
    protected double z;
    
    public Acceleration(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return y;
    }
    
    public double[] toArray() {
        return new double[] {getX(), getY(), getZ()};
    }

    public String toString() {
        return "Acceleration : (" + x + ", " + y + ", " + z + ")";
    }
}
