package morphism.endomorphism;

import morphism.IEndomorphism;
import morphism.coordinates.Coordinate;
import morphism.coordinates.ICoordinateSystem;
import morphism.geo.Polygon2D;
import morphism.geo.Vector2D;

import java.util.Collection;

/**
 * A morphism between two polygons.
 */
public class PolygonMorphism implements IEndomorphism<Vector2D> {

    /**
     * The original polygon
     */
    final private Polygon2D poly;

    /**
     * The transformed polygon
     */
    final private Polygon2D polyTransformed;

    /**
     * Some coordinate system for representing a point relative to a polygon.
     */
    final private ICoordinateSystem<Polygon2D> coordSystem;

    public PolygonMorphism(Collection<Vector2D> vertices, Collection<Vector2D> verticesTransformed, ICoordinateSystem<Polygon2D> coordSystem) {
        this.coordSystem = coordSystem;
        this.poly = new Polygon2D(vertices);
        this.polyTransformed = new Polygon2D(verticesTransformed);
    }

    public PolygonMorphism(Polygon2D poly, Polygon2D transformedPoly, ICoordinateSystem<Polygon2D> coordSystem) {
        this.poly = poly;
        this.polyTransformed = transformedPoly;
        this.coordSystem = coordSystem;
    }

    public IEndomorphism<Vector2D> inverse() {
        return new PolygonMorphism(polyTransformed, poly, coordSystem);
    }

    public Vector2D map(Vector2D p) {
        Coordinate coord = coordSystem.fromCartesian(p);

        return coordSystem.toCartesian(coord, polyTransformed);
    }
}
