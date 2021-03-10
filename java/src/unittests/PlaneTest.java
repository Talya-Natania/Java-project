/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class PlaneTest {

	
	Plane plane=new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		
		try {
		assertEquals("Normal calculation is incorrect",new Vector(1,1,1).normalized(),plane.get_normal());
		}
		catch(IllegalArgumentException e) {
			
		}
		}
		/**
	     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	     */
	    @Test
	    public void testFindIntersections() {
	    	plane=new Plane(new Point3D(1,2,0),new Point3D(0,7,0),new Point3D(1,0,0));
	    	// ============ Equivalence Partitions Tests ==============
	    	//The Ray is not orthogonal or parallel to the plane
	        // TC01: Ray intersects the plane
	    	Point3D p=new Point3D(3,0,0);
	    	 List<GeoPoint> re = plane.findIntsersections(new Ray(new Point3D(0, 0, 3),
                     new Vector(3,0,-3)));
           assertEquals("Wrong number of points", 1, re.size());
           assertEquals("Ray crosses plane ", p, re.get(0).getPoint());
           // TC02: Ray does not intersect the plane
           assertEquals("Ray's line out of plane", null,
           plane.findIntsersections(new Ray(new Point3D(0, 0, 3), new Vector(-3, 0, 3))));
           // =============== Boundary Values Tests ==================
           //**** Group:Ray is parallel to the plane
           //TC11:The ray included in the plane
           assertEquals("Ray's line out of plane", null,
                   plane.findIntsersections(new Ray(new Point3D(0, 2, 0), new Vector(0, 1, 0))));
           //TC12:The ray is not  included in the plane
    assertEquals("Ray's line out of plane", null,
                   plane.findIntsersections(new Ray(new Point3D(0, 0, 3), new Vector(0, 1, 0))));
    //**** Group:Ray is orthogonal to the plane
  //TC13:before the plane
    p=new Point3D(3,0,0);
	 re = plane.findIntsersections(new Ray(new Point3D(3, 0, -3),
            new Vector(0,0,1)));
  assertEquals("Wrong number of points", 1, re.size());
  assertEquals("Ray crosses plane ", p, re.get(0).getPoint());   	
  //TC14:in the plane
  assertEquals("Ray's line in the plane", null,
          plane.findIntsersections(new Ray(new Point3D(3, 0, 0), new Vector(0, 0, 1))));
  //TC14:after the plane 
  assertEquals("Ray's line in the plane", null,
          plane.findIntsersections(new Ray(new Point3D(0, 0, 5), new Vector(0, 0, 1))));
//**** Group:Ray is neither orthogonal nor parallel to
//TC15: begins at the plane    	
  assertEquals("Ray's line in the plane", null,
          plane.findIntsersections(new Ray(new Point3D(3, 4, 0), new Vector(0, 1, 1))));
//TC15: begins at the plane
  assertEquals("Ray's line in the plane", null,
          plane.findIntsersections(new Ray(plane.get_p(), new Vector(0, 1, 1))));
	}
}
