jmorphism
=======================

A Java library for  2D Cage-based Image Transform.

[See a video of  jmorphism in action](http://www.youtube.com/watch?v=eMu-ePsIh8I)

The input is an arbitrary polygon P (not necessarily convex) containing an image (image 1). 
The pixels of (image 1) change their positions, under the influence of the vertices of P.

<img alt="jmorphism demo" src="https://github.com/GHamrouni/jmorphism/raw/master/deformation.jpg" />

Webpage
--------
http://ghamrouni.github.com/jmorphism/

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

Building
--------

    mvn clean install

Contributing
--------

If you'd like to contribute :

1. Fork it and create a branch
1. Hack it and Commit your changes
1. Push to the branch
1. Send a pull request



