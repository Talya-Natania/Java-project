/**
 * 
 */
package elements;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Light source interface
 * @author USER
 *
 */
public interface LightSource {
	/**
	 * 
	 * @param p
	 * @return the color of point
	 */
	public Color getIntensity(Point3D p);
	/**
	 * 
	 * @param p
	 * @return vector - Lighting direction

	 */
	public Vector getL(Point3D p);
	double getDistance(Point3D point);
    public List<Point3D> getPointsGrid(Vector LightDir);

}
