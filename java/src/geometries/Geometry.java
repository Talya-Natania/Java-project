package geometries;

import primitives.Vector;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
public abstract class Geometry implements Intersectable {
protected Color _emmission;
protected Material _material;
/**
 * 
 * c-tor which get color and material
 */
public Geometry(Color c,Material m) {
	 this._emmission = new Color(c);
     this._material = new Material(m);	
}
/**
 * 
 * c-tor which get color
 */
public Geometry (Color c) {
    this(c, new Material(0d, 0d, 0));

}
/*
 * Default c-tor
 */
public Geometry() {
	this(Color.BLACK);
}
/*
 * 
 * Getter for emmission
 */
public Color get_emmission() {
		return _emmission;
	}
/**
 * @return the _material
 */
public Material get_material() {
	return _material;
}
/**
 * @param _material the _material to set
 */
public void set_material(Material _material) {
	this._material = _material;
}
public abstract Vector  getNormal (Point3D inGeomety) ;
}
