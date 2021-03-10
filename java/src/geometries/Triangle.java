package geometries;
import primitives.Color;
import primitives.Material;
/**
 *The class triangle representing triangle by Inheritance from polygon 
 * 
 */
import primitives.Point3D;

public class Triangle extends Polygon {
	/**
	 * C-tor which get 3 points
	 * and send them to the c-tor of polygon
	 * 
	 * */
public Triangle(Color emissionLight,Material m, Point3D p1, Point3D p2, Point3D p3) {
    super(emissionLight,m, p1, p2, p3);
}
public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
    this(emissionLight,new Material(0,0,0), p1, p2, p3);
}
public Triangle(Point3D a,Point3D b,Point3D c){
this(Color.BLACK,new Material(0,0,0),a,b,c);
}
@Override
public String toString() {
    String result = "";
    for (Point3D p : _vertices) {
        result += p.toString();
    }
    return result;
}
}
