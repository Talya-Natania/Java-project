package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable {

	
	private List<Intersectable> _geometries;
	//default c-tor
	public Geometries(){
		_geometries=new ArrayList<Intersectable>();
	}
	//copy-c-tor.copy array of intersections
	public Geometries(Intersectable...mygeometries) {
		_geometries=new ArrayList<Intersectable>();
		for(int i=0;i<mygeometries.length;i++)
		    _geometries.add(mygeometries[i]);
	}
	//function which add new intersections to the list
	 public void add(Intersectable...mygeometries) {
		 for(int i=0;i<mygeometries.length;i++)
			    _geometries.add(mygeometries[i]);
	 }
	@Override
	public List<GeoPoint> findIntsersections(Ray ray) {
		List<GeoPoint> lst=new ArrayList<GeoPoint>();
		for(int i=0;i<_geometries.size();i++) {
			if(_geometries.get(i).findIntsersections(ray)!=null) {
				for(int j=0;j<_geometries.get(i).findIntsersections(ray).size();j++) {
					lst.add(_geometries.get(i).findIntsersections(ray).get(j));
				}
			}
		}
		if(lst.size()==0)
			return null;//No intersection at all
		return lst;
	}

}
