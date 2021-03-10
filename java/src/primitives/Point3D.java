package primitives;
import static primitives.Coordinate.*;

/**
 * Class Point3D is the basic class representing a point
 * The class is based on the coordinate class.
 * 
 * @author 
 */
public class Point3D {
Coordinate x;
Coordinate y;
Coordinate z;
/**
 *C-tor receiving 3 coordinates 
 * */
public Point3D(Coordinate a,Coordinate b,Coordinate c){

	x=a;
	y=b;
	z=c;
}
/**
 *C-tor receiving 3 double 
  */
public Point3D(double a,double b,double c){
	x=new Coordinate(a);
	y=new Coordinate(b);
	z=new Coordinate(c);
}
/**
 * Copy c-tor receiving point3d 
 * */
public Point3D(Point3D point){
	x=point.x;
	y=point.y;
	z=point.z;
}
//Static field point3D contain  (0.0.0) -
public static Point3D ZERO=new Point3D(0,0,0);
/** 
 * Vector Subtraction - Gets a second point in the parameter, 
 * returns a vector from the second point to the point at which the action is performedVector Subtraction 
 */
public Vector subtract(Point3D point) {
	return new Vector(x.get()-point.x.get(),y.get()-point.y.get(),z.get()-point.z.get());
}/**
 Adding Vector to a Point - Returns a new point
*/
public Point3D add(Vector vec) {
	return new Point3D(vec.getStart().x.get()+x.get(),vec.getStart().y.get()+y.get(),vec.getStart().z.get()+z.get());
}
/**
 * The distance between 2 points squared
 */
public double distanceSquared(Point3D point) {
	return (point.x.get()-x.get())*(point.x.get()-x.get())+(point.y.get()-y.get())*(point.y.get()-y.get())+(point.z.get()-z.get())*(point.z.get()-z.get());
}
/**
 * The distance between 2 points 
 */
public double distance(Point3D point) {
	return Math.sqrt(distanceSquared(point));
}
public Coordinate getX() {
	return x;
}
public Coordinate getY() {
	return y;
}
public Coordinate getZ() {
	return z;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Point3D other = (Point3D) obj;
	return x.equals(other.x)&&y.equals(other.y)&&z.equals(other.z);
	}
@Override
public String toString() {
	return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
}


}


