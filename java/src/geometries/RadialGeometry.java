package geometries;

import primitives.Color;
import primitives.Material;

public abstract class RadialGeometry extends Geometry  {
double _radius;
RadialGeometry(Color emmission,Material m,double r){
	super(emmission,m);
	if(r<=0)
		throw new IllegalArgumentException ("The radius has to be posotive number");
	_radius=r;
	
}
RadialGeometry(Color emmission,double r){
	this(emmission,new Material(0,0,0),r);
}

RadialGeometry(double r){
	this(Color.BLACK,new Material(0,0,0),r);
}
RadialGeometry(RadialGeometry rg){
	super(rg._emmission,rg._material);
	_radius=rg._radius;
}

public double get_radius() {
	return _radius;
}
}
