package morphism.geo;

/**
 * A line/segment in 2D space.
 */
public class Line2D {

    final private Vector2D a;

    final private Vector2D b;

    public Line2D(Vector2D a, Vector2D b) {
        this.a = a;
        this.b = b;
    }

    /**
     * A line is formed by two points A and B
     *
     * @return return the point A
     */
    public Vector2D getA() {
        return a;
    }

    /**
     * A line is formed by two points A and B
     *
     * @return return the point B
     */
    public Vector2D getB() {
        return b;
    }

    /**
     * Get a vector having the same orientation as the line A-B
     *
     * @return A vector AB = B - A
     */
    public Vector2D getVector() {
        return (b.minus(a)).normalized();
    }

    /**
     * Calculate the length of the segment AB using
     * the euclidean distance d(A, B) = || B - A ||
     *
     * @return The length of the segment AB
     */
    public double length() {
        return (b.minus(a)).length();
    }

    /**
     * Calculate the euclidean distance between a point
     * and the line AB.
     *
     * @param p a point in the 2d space.
     * @return the distance between AB and p
     */
    public double distanceToPoint(Vector2D p) {
        Vector2D n = (b.minus(a)).normalized();

        return ((a.minus(p)).minus(n.times((a.minus(p)).dot(n)))).length();
    }

    /**
     * Get a point from the line
     *
     * @param alpha if alpha is in [0..1] the point lies in the segment [A, B].
     * @return A point from this line
     */
    public Vector2D getPointAt(double alpha) {
        Vector2D vector2DAB = new Vector2D(b.getX() - a.getX(), b.getY() - a.getY());

        return new Vector2D(a.getX() + alpha * vector2DAB.getX(), a.getY() + alpha * vector2DAB.getY());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line2D line = (Line2D) o;

        if (!a.equals(line.a)) return false;
        if (!b.equals(line.b)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Line2D{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
