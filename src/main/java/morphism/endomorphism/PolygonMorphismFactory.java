package morphism.endomorphism;

import morphism.coordinates.BarycentricCoordinateSystem;
import morphism.coordinates.GreenCoordinateSystem;
import morphism.geo.Polygon2D;

public class PolygonMorphismFactory {
    public static PolygonMorphism get(PolygonMorphismKind kind, Polygon2D poly, Polygon2D polyT)
    {
        switch (kind)
        {
            case ShapePreserving:
                return new PolygonMorphism(poly, polyT, new GreenCoordinateSystem(poly));
            case Rigid:
                 return new PolygonMorphism(poly, polyT, new BarycentricCoordinateSystem(poly));

        }

        return null;
    }
}
