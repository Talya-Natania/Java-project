/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class SphereTest {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Sphere sp=new Sphere(1, new Point3D(0,0,0));
		try {
		assertEquals("Normal calculation is incorrect",new Vector(1,0,0),sp.getNormal(new Point3D(1,0,0)));
		}
		catch(IllegalArgumentException e) {
		}	
	}
	   /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere 
        assertEquals("Ray's line out of sphere", null,
                        sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        GeoPoint p1 = new GeoPoint(sphere,new Point3D(0.0651530771650466, 0.355051025721682, 0));
        GeoPoint p2 = new GeoPoint(sphere,new Point3D(1.53484692283495, 0.844948974278318, 0));
        List<GeoPoint> result = sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0),new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getPoint().getX().get() > result.get(1).getPoint().getX().get())
            result = List.of(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere 
        GeoPoint p3=new GeoPoint(sphere,new Point3D(1,1,0));
        List<GeoPoint> myresult = sphere.findIntsersections(new Ray(new Point3D(1, 0.5, 0),
                new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 1, myresult.size());
        assertEquals("Ray crosses sphere",p3,myresult.get(0));
        // TC04: Ray starts after the sphere 
        assertEquals("Ray's line starts after the sphere", null,
                sphere.findIntsersections(new Ray(new Point3D(-1, 0, 0), new Vector(0,- 1, 0))));


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        GeoPoint  p4=new GeoPoint(sphere,new Point3D(2,0,0));
        List<GeoPoint> _result = sphere.findIntsersections(new Ray(new Point3D(1, 1, 0),
                new Vector(1, -1, 0)));
        assertEquals("Wrong number of points", 1, myresult.size());
        assertEquals("Ray crosses sphere",p4,_result.get(0));
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntsersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 2, 0))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        GeoPoint p5 = new GeoPoint(sphere,new Point3D(1,-1, 0));
        GeoPoint p6 = new GeoPoint(sphere,new Point3D(1,1, 0));
        List<GeoPoint> res = sphere.findIntsersections(new Ray(new Point3D(1, 2, 0),
                                                                 new Vector(0,-4,0)));
        assertEquals("Wrong number of points", 2, res.size());
        if (res.get(0).getPoint().getY().get() > res.get(1).getPoint().getY().get())
            res = List.of(res.get(1), res.get(0));
        assertEquals("Ray crosses sphere", List.of(p5, p6), res);
        // TC14: Ray starts at sphere and goes inside (1 points)
        GeoPoint p7 = new GeoPoint(sphere,new Point3D(1,-1, 0));
        List<GeoPoint> _res = sphere.findIntsersections(new Ray(new Point3D(1, 1, 0),
                                                                 new Vector(0,-4,0)));
        assertEquals("Wrong number of points", 1, _res.size());
        assertEquals("Ray crosses sphere", p7, _res.get(0));
        // TC15: Ray starts inside (1 points)
        List<GeoPoint> re = sphere.findIntsersections(new Ray(new Point3D(1, -0.5, 0),
                                                                new Vector(0,-1.5,0)));
        assertEquals("Wrong number of points", 1, re.size());
        assertEquals("Ray crosses sphere", p7, re.get(0));
        // TC16: Ray starts at the center (1 points)
         re = sphere.findIntsersections(new Ray(new Point3D(1, 0, 0),
                                                                new Vector(0,-2,0)));
    	 assertEquals("Wrong number of points", 1, re.size());
        assertEquals("Ray crosses sphere", p7, re.get(0)); 
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntsersections(new Ray(new Point3D(1, -1, 0), new Vector(0, -1, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntsersections(new Ray(new Point3D(2, -1, 0), new Vector(1.5, -1.5, 0))));
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntsersections(new Ray(new Point3D(0, -1, 0), new Vector(-1, 0, 0))));
        // TC20: Ray starts at the tangent point
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntsersections(new Ray(new Point3D(1, -1, 0), new Vector(1, 0, 0))));
        // TC21: Ray starts after the tangent point
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntsersections(new Ray(new Point3D(1.48, -1, 0), new Vector(1, 0, 0))));
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntsersections(new Ray(new Point3D(3, 0, 0), new Vector(0, 2, 0))));

    }


}
