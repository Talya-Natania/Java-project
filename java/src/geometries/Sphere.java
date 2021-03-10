package geometries;
import java.util.ArrayList;
import java.util.List;
import primitives.Util;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

public class Sphere extends RadialGeometry {
Point3D _center;
public Sphere(Color emmission,Material m,double r,Point3D center) {
	super(emmission,m,r);
	_center=center;
	}
public Sphere(Color emmission,double r,Point3D center) {
	this(emmission,new Material(0,0,0),r,center);
}
public Sphere(double r,Point3D center) {
	this(Color.BLACK,new Material(0,0,0),r,center);
}
public Sphere(Sphere s){
	super(s._emmission,s._material,s._radius);
	_center=s._center;	
}
public Point3D get_center() {
	return _center;
}
@Override
public String toString() {
	return "Sphere [_center=" + _center + "radius=" +super.get_radius()+ "]";
}
@Override
public Vector getNormal(Point3D inGeomety) {
	Vector p=inGeomety.subtract(_center);
	return p.normalized();
}
@Override
public List<GeoPoint> findIntsersections(Ray ray) {
	Vector v=ray.getDirection();
	Point3D Po=ray.getStart();
	Vector u;
	//if(Po.equals(_center))
		//throw new IllegalArgumentException("The entry point is the center");
	try {
	 u=_center.subtract(Po);}
	catch (IllegalArgumentException e){
		return List.of(new GeoPoint(this,ray.getPoint(_radius)));
	}
	double Tm=Util.alignZero(u.dotProduct(v));
	double d=Math.sqrt(u.lengthSquared()-(Tm*Tm));
	if(d>this._radius)
	return null;
	double Th=Util.alignZero(Math.sqrt(this._radius*this._radius-d*d));
	double T1=Util.alignZero(Tm+Th);
	double T2=Util.alignZero(Tm-Th);
	if(T1>0||T2>0) 
	{ 	
    List<GeoPoint> lst=new ArrayList<GeoPoint>();
    if(T1>0) {
    GeoPoint P1=new GeoPoint(this,ray.getPoint(T1));
	lst.add(P1);
	}
	
	if(T2>0) 
	{
		GeoPoint P2=new GeoPoint(this, ray.getPoint(T2));
		lst.add(P2);
    }
	return lst;
	}
	return null;
  }
	
}





