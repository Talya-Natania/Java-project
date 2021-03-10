package elements;

import primitives.Color;
/**
 * Class for light
 * @author USER
 *field:color:intensity
 */
public abstract class Light {
	protected Color _intensity;
	/**
	 * C-tor which get intensity
	 * @param _intensity
	 */
	public Light(Color _intensity) {
		this._intensity = _intensity;
	}
	/**
	 * field for intensity and getter
	 */
	
	public Color get_intensity() {
		return _intensity;
	}
	
	

}
