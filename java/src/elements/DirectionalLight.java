/**
 * 
 */
package elements;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class DirectionalLight extends Light implements LightSource{
	private Vector _direction ;
/**
 * 
 * @param _intensity
 * @param my_direction
 */
	public DirectionalLight(Color _intensity, Vector my_direction) {
		super(_intensity);
		_direction=my_direction.normalized();
		
	}

	@Override
	public Color getIntensity(Point3D p) {
		return super.get_intensity();
	}

	@Override
	public Vector getL(Point3D p) {
		return _direction;
	}

	@Override
	public double getDistance(Point3D point) {
		return Double.POSITIVE_INFINITY;
	}

	@Override
	public List<Point3D> getPointsGrid(Vector l) {
		return null;
	}

	

	
	

}
