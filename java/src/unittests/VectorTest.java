
package unittests;

import static java.lang.System.out;
//import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;
/**Unit tests for primitives.Vector class
 * @author Naama&Talya
 *
 */
public class VectorTest {
	Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		Point3D p1 = new Point3D(1, 2, 3);
		try {
			assertEquals("ERROR: Point - Vector does not work correctly",new Vector(1, 1, 1),new Point3D(2, 3, 4).subtract(p1));
		
		}
catch(IllegalArgumentException e) {
			
		}        
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		Point3D p1 = new Point3D(1, 2, 3);
		try {
		assertEquals("ERROR: Point + Vector does not work correctly",Point3D.ZERO,p1.add(new Vector(-1, -2, -3)));
		}
		catch(IllegalArgumentException e) {
			
		}
	}

	/**
	 * Test method for {@link primitives.Vector#scale(int)}.
	 */
	@Test
	public void testScale() {
		// ============ Equivalence Partitions Tests ==============
		try {
			assertEquals("ERROR: scalar*vector-does not work correctly",new Vector(2,4,6),v1.scale(2));
			}
			catch(IllegalArgumentException e) {
				
			}	
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		// ============ Equivalence Partitions Tests ==============
		try {
		assertTrue(" dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));
		assertTrue("dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28)); 
	}
		catch(IllegalArgumentException e) {
			
		}	
		}
	
	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
       
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		try {
		assertTrue("lengthSquared() wrong value",isZero(v1.lengthSquared() - 14));
		}
		catch(IllegalArgumentException e) {
			
		}	
		
	}
	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() { try {
		assertTrue("length() wrong value",isZero(new Vector(0, 3, 4).length() - 5));
	}
	catch(IllegalArgumentException e) {
		
	}	
	     
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		 Vector v = new Vector(1, 2, 3);
	        Vector vCopy = new Vector(v);
	        Vector vCopyNormalize = vCopy.normalize();
	        try {
	        assertEquals("normalize() function creates a new vector",vCopy,vCopyNormalize);
	        assertTrue("normalize() result is not a unit vector",isZero(vCopyNormalize.length() - 1));
	        }catch(IllegalArgumentException e) {
				
			}	
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v = new Vector(1, 2, 3);
		Vector u = v.normalized();
		try {
			assertTrue("ERROR: normalizated() function does not create a new vector",u!=v);
		}
	catch(IllegalArgumentException e) {
		
	}
        
	}

}
