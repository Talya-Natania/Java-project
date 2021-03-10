package geometries;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
 * The class cylinder representing cylinder by radius and hight
 * @author USER
 *
 */
public class Cylinder extends RadialGeometry {
double hight;
/**
 * C-tor which get 2 double points*/
	Cylinder(Color emmission,double r,double h) {
		super(emmission,r);
		// TODO Auto-generated constructor stub
		hight=h;
	}
	Cylinder(double r,double h) {
		this(Color.BLACK,r,h);
	}
	/**
	 * Copy C-tor*/
	Cylinder(Cylinder cy){
		super(cy._emmission,cy._radius);
		hight=cy.hight;
	}
	public double getHight() {
		return hight;
	}
	
	@Override
	public String toString() {
		return "Cylinder [hight=" + hight +"radius=" +super.get_radius()+ "]";
	}
	@Override
	public Vector getNormal(Point3D inGeomety) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<GeoPoint> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
