package morphism.geo;

/**
 * An immutable 2D vector of double, used to represent vector
 * and points in the 2D space.
 * (Perhaps it would be better to separate points and vector).
 */
public class Vector2D {

    final private double x;

    final private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public Vector2D plus(Vector2D v1) {
        return new Vector2D(x + v1.x, y + v1.y);
    }

    public Vector2D minus(Vector2D v) {
        return new Vector2D(x - v.x, y - v.y);
    }

    public Vector2D times(double c) {
        return new Vector2D(x * c, y * c);
    }

    public double crossLength(Vector2D v) {
        return Math.abs(x * v.y - y * v.x);
    }

    public double dot(Vector2D v) {
        return x * v.x + y * v.y;
    }

    public double length2() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalized() {
        double l = length();

        if (l == 0.0)
            return new Vector2D(0, 0);

        return new Vector2D(x / l, y / l);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (Double.compare(vector2D.x, x) != 0) return false;
        if (Double.compare(vector2D.y, y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
