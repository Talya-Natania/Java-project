/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * Testing Polygons
 * @author Dan
 *
 */
public class PolygonTests {
    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
    /**
     * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
    	Polygon poly=new Polygon(new Point3D(0,4,0),new Point3D(0,1,0),new Point3D(3,1,0));
    	// ============ Equivalence Partitions Tests ==============
        // TC01: Inside polygon/triangle
    	 
    Point3D p=new Point3D(1,2,0);
    	 List<GeoPoint> re = poly.findIntsersections(new Ray(new Point3D(0, 0, 3),
                 new Vector(1,2,-3)));
       assertEquals("Wrong number of points", 1, re.size());
       assertEquals("Ray crosses polygon ", p, re.get(0).getPoint());
    	//TC02:Outside against edge
       assertEquals("Ray's line out of plane", null,
               poly.findIntsersections(new Ray(new Point3D(0, 0, 3), new Vector(2, 3, -3))));
       //TC03:Outside against vertex
       assertEquals("Ray's line out of plane", null,
               poly.findIntsersections(new Ray(new Point3D(0, 0, 3), new Vector(-1, 0, -3))));
       // =============== Boundary Values Tests ==================
       //TC11:On edge
       assertEquals("Ray's line out of plane", null,
               poly.findIntsersections(new Ray(new Point3D(0, 0, 3), new Vector(1, 3, -3))));
       //TC12:In vertex
       assertEquals("Ray's line out of plane", null,
               poly.findIntsersections(new Ray(new Point3D(0, 0, 3), new Vector(0, 4, -3))));
       //TC13:On edge's continuation
       assertEquals("Ray's line out of plane", null,
               poly.findIntsersections(new Ray(new Point3D(0, 0, 3), new Vector(4, 1, -3))));
    	
    }

}

