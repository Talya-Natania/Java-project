/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class SpotLight extends PointLight {
	
	private Vector _direction;

	public SpotLight(Color _intensity ,Point3D position, Vector direction, double kC, double kL, double kQ) {
	
		super(_intensity,position,kC,kL,kQ);
		 this._direction=new Vector(direction).normalized();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);
        Color pointlightIntensity = super.getIntensity(p);

        return (pointlightIntensity.scale(factor));
    }

}
