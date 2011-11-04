package morphism.coordinates;

import morphism.geo.Polygon2D;
import morphism.geo.Vector2D;

import java.util.Vector;

/**
 *   Green Coordinates lead to shape-preserving deformations.
 *
 *   Based on the paper: Green Coordinates  (Yaron Lipman, David Levin, Daniel Cohen-Or) Tel-Aviv University
 */
public class GreenCoordinateSystem implements ICoordinateSystem<Polygon2D> {

    private final Polygon2D refPolygon;

    public GreenCoordinateSystem(Polygon2D refPolygon) {
        this.refPolygon = refPolygon;
    }

    public Coordinate fromCartesian(Vector2D point) {

        int n = refPolygon.count();
        Coordinate coordinate = new Coordinate(n * 2);

        double[] Psi = new double[n];
        double[] Phi = new double[n];

        for (int k = 0; k < n; k++) {
            int next = (k + 1) % n;

            Vector2D v1 = refPolygon.get(k);
            Vector2D v2 = refPolygon.get(next);

            Vector2D a = v2.minus(v1);
            Vector2D b = v1.minus(point);

            double Q = a.dot(a);
            double S = b.dot(b);
            double R = 2 * a.dot(b);

            double BA = b.dot(refPolygon.getOutwardNormal(k).times(a.length()));

            double SRT = Math.sqrt(4 * S * Q - R * R);

            double L0 = Math.log(S);
            double L1 = Math.log(S + Q + R);

            double A0 = Math.atan(R / SRT) / SRT;
            double A1 = Math.atan((2 * Q + R) / SRT) / SRT;

            double A10 = A1 - A0;
            double L10 = L1 - L0;

            Psi[k] = -a.length() / (4 * Math.PI) * ((4 * S - R * R / Q) * A10 + R / (2 * Q) * L10 + L1 - 2);
            Phi[next] = Phi[next] - BA / (2 * Math.PI) * (L10 / (2 * Q) - A10 * R / Q);
            Phi[k] = Phi[k] + BA / (2 * Math.PI) * (L10 / (2 * Q) - A10 * (2 + R / Q));
        }

        double phiSum = 0;
        double psiSum = 0;

        for (int i = 0; i < n; i++) {
            phiSum = phiSum + Math.abs(Phi[i]);
            psiSum = psiSum + Psi[i];
        }

        for (int i = 0; i < n; i++) {
            Phi[i] = (-1 * Phi[i]) * (1.0 / phiSum);
        }

        for (int i = 0; i < n; i++) {
            coordinate.setCoefficient(i, Phi[i]);
            coordinate.setCoefficient(n + i, Psi[i]);
        }

        return coordinate;
    }

    public Vector2D toCartesian(Coordinate point) {

        int n = refPolygon.count();
        Vector2D f = new Vector2D(0, 0);

        for (int i = 0; i < n; i++) {
            Vector2D tj = (refPolygon.getOutwardNormal(i)).normalized();

            f = f.plus(refPolygon.get(i).times(point.getCoefficient(i)).plus(tj.times(point.getCoefficient(n + i))));
        }

        return f;
    }

    public Vector2D toCartesian(Coordinate point, Polygon2D refPolygonT) {
        int n = refPolygon.count();
        Vector2D f = new Vector2D(0, 0);

        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            double scaleFactor = (refPolygonT.get(next).minus(refPolygonT.get(i))).length() / (refPolygon.get(next).minus(refPolygon.get(i))).length();

            Vector2D tj = (refPolygonT.getOutwardNormal(i)).normalized();

            f = f.plus(refPolygonT.get(i).times(point.getCoefficient(i)).plus(tj.times(point.getCoefficient(n + i) * scaleFactor)));
        }

        return f;
    }
}
