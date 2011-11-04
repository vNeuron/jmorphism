package morphism.coordinates;

import morphism.geo.Polygon2D;
import morphism.geo.Vector2D;

/**
 * Calculate the generalized barycentric coordinates.
 *
 * Based on the paper:
 * Barycentric Coordinates for Arbitrary Polygons in the Plane.
 * (Kai Hormann) Institute of Computer Science, Clausthal University of Technology, Germany
 */
public class BarycentricCoordinateSystem implements ICoordinateSystem<Polygon2D> {

    private final Polygon2D refPolygon;

    public BarycentricCoordinateSystem(Polygon2D refPolygon) {
        this.refPolygon = refPolygon;
    }

    public Coordinate fromCartesian(Vector2D point) {
        int n = refPolygon.count();

        Coordinate coordinate = new Coordinate(n);

        Vector2D[] si = new Vector2D[n];

        double[] Ai = new double[n];
        double[] Di = new double[n];
        double[] ri = new double[n];

        for (int i = 0; i < n; i++)
            si[i] = (refPolygon.get(i).minus(point));

        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;

            ri[i] = (si[i].length());

            Ai[i] = (si[i].crossLength(si[next]) * 0.5);
            Di[i] = (si[i].dot(si[next]));

            if (ri[i] == 0.0) {
                coordinate.setCoefficient(i, 1.0);
                return coordinate;
            }

            if (Ai[i] == 0.0 && Di[i] < 0) {
                ri[next] = si[next].length();

                coordinate.setCoefficient(i, ri[next] / (ri[i] + ri[next]));
                coordinate.setCoefficient(next, ri[i] / (ri[i] + ri[next]));

                return coordinate;
            }
        }

        double W = 0;

        for (int i = 0; i < n; i++) {
            double w = 0;
            int prev = (i + n - 1) % n;
            int next = (i + 1) % n;

            if (Ai[prev] != 0.0)
                w = w + (ri[prev] - Di[prev] / ri[i]) / Ai[prev];

            if (Ai[i] != 0.0)
                w = w + (ri[next] - Di[i] / ri[i]) / Ai[i];

            coordinate.setCoefficient(i, w);

            W = W + w;
        }

        for (int i = 0; i < n; i++) {
            coordinate.setCoefficient(i, coordinate.getCoefficient(i) / W);
        }

        return coordinate;
    }

    public Vector2D toCartesian(Coordinate point) {
        Vector2D f = new Vector2D(0, 0);
        int n = refPolygon.count();

        for (int i = 0; i < n; i++) {
            f = f.plus(refPolygon.get(i).times(point.getCoefficient(i)));
        }

        return f;
    }

    public Vector2D toCartesian(Coordinate point, Polygon2D refPolygonT) {
        Vector2D f = new Vector2D(0, 0);
        int n = refPolygonT.count();

        for (int i = 0; i < n; i++) {
            f = f.plus(refPolygonT.get(i).times(point.getCoefficient(i)));
        }

        return f;
    }
}
