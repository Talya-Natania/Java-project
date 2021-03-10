package geometries;
import java.util.List;

import primitives.Color;
import primitives.Material;
/**
 * Tube class represents tube with ray and radius
 * 
 * @author 
 */
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
Ray _axisRay;
/**
 * C-tor which get double radius and ray(using super function)
 * */

	public Tube(Color emmission,Material m,double r,Ray ray) {
		super(emmission,m,r);
		_axisRay=ray;
	}
	public Tube(Color emmission,double r,Ray ray) {
		this(emmission,new Material(0,0,0),r,ray);
	}
	public Tube(double r,Ray ray) {
		this(Color.BLACK,new Material(0,0,0),r,ray);
	}
	/**
	 * Copy c-tor 
	 */
	
	Tube(Tube tube){
		super(tube._emmission,tube._material,tube._radius);
		_axisRay=tube._axisRay;
	}
	
	public Ray get_axisRay() {
		return _axisRay;
	}
	@Override
	public String toString() {
		return "Tube [_axisRay=" + _axisRay +"radius=" +super.get_radius()+ "]";
	}
	@Override
	public Vector getNormal(Point3D inGeomety) {
		double t=_axisRay.getDirection().dotProduct(new Vector(inGeomety.subtract(_axisRay.getStart())));
		Point3D O=_axisRay.getStart().add(_axisRay.getDirection().scale(t));
		return new Vector(inGeomety.subtract(O)).normalized();
	}
	@Override
	public List<GeoPoint> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
