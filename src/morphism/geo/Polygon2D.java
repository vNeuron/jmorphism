package morphism.geo;

import java.util.*;

/**
 * Represents an arbitrary 2D polygon.
 */
public class Polygon2D implements Iterable<Vector2D> {

    final private Vector<Vector2D> vertices;
    final private Vector2D[] outwardNormals;

    public Polygon2D(Collection<Vector2D> vertices) {
        this.vertices = new Vector<Vector2D>(vertices);

        this.outwardNormals = new Vector2D[vertices.size()];

        calculateNormals();
    }

    public Polygon2D(Vector2D... vertices) {
        this.vertices = new Vector<Vector2D>();

        //FIXME: This is inefficient !
        for (Vector2D vertex : vertices) {
            this.vertices.add(vertex);
        }

        this.outwardNormals = new Vector2D[this.vertices.size()];

        calculateNormals();
    }

    /**
     * Test is p is inside the polygon.
     * Implementation details here http://paulbourke.net/geometry/insidepoly/
     *
     * @param p A point in the 2D space.
     * @return true if the points lies inside the polygon
     *         false otherwise.
     */
    public boolean intersect(Vector2D p) {
        int n = vertices.size();

        double angle = 0;

        for (int i = 0; i < n; i++) {
            Vector2D p1 = vertices.get(i).minus(p);
            Vector2D p2 = vertices.get((i + 1) % n).minus(p);

            angle += angle2D(p1, p2);
        }

        if (Math.abs(angle) < Math.PI)
            return false;
        else
            return true;
    }

    /**
     * Calculate all outward normals of the polygon.
     * FIXME: This is not correct for arbitrary polygon !
     */
    private void calculateNormals() {
        Vector2D center = new Vector2D(0, 0);

        for (Vector2D vertex : vertices) {
            center = center.plus(vertex);
        }

        center = center.times(1.0 / vertices.size());

        int n = vertices.size();
        for (int k = 0; k < n; k++) {
            int next = (k + 1) % n;

            Vector2D v1 = vertices.get(k);
            Vector2D v2 = vertices.get(next);

            outwardNormals[k] = getOutwardNormal(v1, v2);
        }
    }

    /**
     * Get an outward normal formed by the edge v1 -> v2
     * FIXME: This is not correct for arbitrary polygon !
     * @param v1 The first vertex of the edge
     * @param v2 The second vertex of the edge
     * @return An non normalized outward normal
     */
    private Vector2D getOutwardNormal(Vector2D v1, Vector2D v2) {
        double dx = v2.getX() - v1.getX();
        double dy = v2.getY() - v1.getY();

        Vector2D n = (new Vector2D(dy, -dx));// *(1 / v.Length());

        return n;
    }

    /**
     * Get outward normal at a given index
     * @param index the index of the normal
     * @return A non normalized outward normal.
     */
    public Vector2D getOutwardNormal(int index) {
        return outwardNormals[index];
    }

    private double angle2D(Vector2D p1, Vector2D p2) {
        double dtheta, theta1, theta2;

        theta1 = Math.atan2(p1.getY(), p1.getX());
        theta2 = Math.atan2(p2.getY(), p2.getX());

        dtheta = theta2 - theta1;

        while (dtheta > Math.PI)
            dtheta -= Math.PI * 2;

        while (dtheta < -Math.PI)
            dtheta += Math.PI * 2;

        return (dtheta);
    }

    /**
     * Get the number of the vertices in the polygon.
     *
     * @return vertices size
     */
    public int count() {
        return vertices.size();
    }

    /**
     * Get a vertex from the polygon
     *
     * @param i the index of the vertex
     * @return the vertex having the specified index.
     */
    public Vector2D get(int i) {
        return vertices.get(i);
    }

    public Iterator<Vector2D> iterator() {
        return vertices.iterator();
    }
}
