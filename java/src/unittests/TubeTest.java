/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class TubeTest {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Tube tube=new Tube(5,new Ray(new Point3D(1,2,3),new Vector(0,3,0)));
		Point3D p=new Point3D(3,4,6);
		try {
			assertEquals("tube doesn't calculate the normal right",new Vector(2,0,3).normalized(), tube.getNormal(p));
		}
	catch(IllegalArgumentException e) {
		
	}	
		
	}

}
