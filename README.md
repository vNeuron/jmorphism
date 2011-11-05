jmorphism
=======================

A Java library for  2D Cage-based Image Transform.

[See a video of  jmorphism in action](http://www.youtube.com/watch?v=eMu-ePsIh8I)


Using this library
------------------

Given two polygons poly and polyT formed by a clockwise collections of vertices:

    Collection<Vector2D> vertices  = (...)
    Collection<Vector2D> verticesT = (...) 

    Polygon2D poly  = new Polygon2D(vertices);
    Polygon2D polyT = new Polygon2D(verticesT);

jmorphism can create a one-to-one correspondence mapping a point in poly to a point in polyT:

    PolygonMorphism morphism = PolygonMorphismFactory.get(PolygonMorphismKind.Rigid, poly, polyT);

morphism is a structure preserving mapping. Two kinds of morphisms are available:

* Rigid: Uses internally the generalized barycentric coordinates
* ShapePreserving: Uses internally the Green Coordinates that lead to shape-preserving deformations

To compute the image of a point lying inside the polygon poly:

    Vector2D p2 = morphism.map(p);//p2 is inside polyT

