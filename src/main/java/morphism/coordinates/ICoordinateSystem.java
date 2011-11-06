package morphism.coordinates;

import morphism.geo.Vector2D;

/**
 * A system for representing points using coordinates.
 *
 * Coordinates are determined according to some reference.
 * For example cartesian coordinates are distances measured
 * along perpendicular axes.
 *
 * @param <R> The type of the reference.
 */
public interface ICoordinateSystem<R> {

    /**
     * Represent a point in this coordinate system.
     *
     * @param point A point in the cartesian coordinate system.
     * @return A point represented in the current coordinate system
     */
    Coordinate fromCartesian(Vector2D point);

    /**
     * Represent a point in the cartesian coordinate system.
     *
     * @param point A point in the current coordinate system.
     * @return A point represented in the cartesian coordinate system
     */
    Vector2D toCartesian(Coordinate point);

    /**
     * Represent a point in the cartesian coordinate system.
     *
     * @param point     A point in the current coordinate system.
     * @param reference The coordinates are measured according to a reference.
     * @return A point represented in the cartesian coordinate system
     */
    Vector2D toCartesian(Coordinate point, R reference);
}
