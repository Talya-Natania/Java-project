package geometries;
import static primitives.Util.*;
import java.util.ArrayList;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
 * Plane class represents a plane in space
 * using point3D and vector(normal)
 * 
 */
public class Plane extends Geometry {
Point3D _p;
Vector _normal;
/**
 * C-tor which get 3 points*/

public Plane( Color emmission,Material m,Point3D a,Point3D b,Point3D c){
	super(emmission,m);
	_p=new Point3D(a);
	Vector v=new Vector(a.subtract(b));
	Vector u=new Vector(b.subtract(c));
	_normal=v.crossProduct(u).normalized();
	//this._material=m;
	
}
public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
    this(emissionLight, new Material(0, 0, 0), p1, p2, p3);
}
public Plane(Point3D a,Point3D b,Point3D c){
	this(Color.BLACK,new Material(0, 0, 0),a,b,c);
}
/**
 * C-tor which get point and vector*/

public Plane(Color emmission, Point3D a,Vector vec){
	super(emmission,new Material(0, 0, 0));
	_p=a;
	_normal=vec;
}
public Plane( Point3D a,Vector vec){
	super(Color.BLACK, new Material(0, 0, 0));

    this._p = new Point3D(_p);
    this._normal = new Vector(_normal);
    
}


	public Point3D get_p() {
	return _p;
}

public Vector get_normal() {
	return _normal;
}

	@Override
	public Vector getNormal(Point3D inGeomety) {
		return _normal;
	}
	@Override
	public String toString() {
		return "Plane [_p=" + _p + ", _normal=" + _normal + "]";
	}

	@Override
	public List<GeoPoint> findIntsersections(Ray ray) {
		Vector v=ray.getDirection();
		Point3D Po=ray.getStart();
		if(Po.equals(_p))
			return null;	
		double nv =_normal.dotProduct(v); 
		if (isZero(nv))
			return null;
		double t=_normal.dotProduct((_p.subtract(Po)))/nv;
		if(t>0) {
			List<GeoPoint> lst=new ArrayList<GeoPoint>();
			GeoPoint P1=new GeoPoint(this,ray.getPoint(t));
			lst.add(P1);
			return lst;
			}
	return null;
	}

}
