/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Geometries;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class GeometriesTest {

	/**
	 * Test method for {@link geometries.Geometries#findIntsersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntsersections() {
		Geometries geo=new Geometries();
		Geometries geo2=new Geometries(new Sphere(5,new Point3D(4,0,0)),new Polygon(new Point3D(1,4,0),new Point3D(1,2,0),new Point3D(5,2,0)),new Plane(new Point3D(1,2,0),new Point3D(0,7,0),new Point3D(1,0,0)));

		 // ============ Equivalence Partitions Tests ==============
        // TC01: Some (but not all) geometries are cut  
		 List<GeoPoint> re = geo2.findIntsersections(new Ray(new Point3D(2, 5, 4),
                 new Vector(2,-4,-4)));
       assertEquals("Wrong number of points", 3, re.size());
       // =============== Boundary Values Tests ==================
       //TC11:Empty geometries collection
     assertEquals("Wrong number of points", null,geo.findIntsersections(new Ray(new Point3D(2, 5, 4),
               new Vector(2,-4,-4))));
     //TC12:No shape is cut
     assertEquals("Wrong number of points", null,geo2.findIntsersections(new Ray(new Point3D(0, 0, 7),
             new Vector(1,0,0))));
     //TC13:One shape is cut
      re = geo2.findIntsersections(new Ray(new Point3D(0, 0, 10),
             new Vector(-8,1,-10)));
   assertEquals("Wrong number of points",1 , re.size());
   //TC14:All shapes are cut
   re = geo2.findIntsersections(new Ray(new Point3D(2, 5, 4),
           new Vector(0,-2,-4)));
 assertEquals("Wrong number of points",4 , re.size());
	}

}
