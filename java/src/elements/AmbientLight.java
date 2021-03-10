package elements;

import primitives.Color;
/**
 * class which extends from light
 * @author USER
 *
 */
public class AmbientLight extends Light {

	/**
	 * C-tor which return the ambient light =ka*ia
	 * @param Ia
	 * @param Ka
	 */
	public AmbientLight(Color Ia,double Ka) {
		super(Ia);
		this._intensity=this._intensity.scale(Ka);
	}
	
}
