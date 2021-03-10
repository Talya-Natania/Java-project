package primitives;
/**
 * Class Ray the basic class representing foundational object in geometry 
 * Defined by point and direction (unit vector).
 * 
 * @author 
 */
public class Ray {
Point3D start;
Vector direction;
private static final double DELTA = 0.1;
/**
 * C-tor receives a point and the direction vector
 */
public Ray(Point3D point,Vector vec) {
	direction=new Vector(vec.normalize());
	start=new Point3D(point);	
}
public Ray(Point3D point, Vector _direction, Vector normal) {
    //point + normal.scale(±DELTA)
   direction = new Vector(_direction).normalized();
    double nv = normal.dotProduct(_direction);
    Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
    start = point.add(normalDelta);
}
public Point3D getStart() {
	return start;
}
public Vector getDirection() {
	return direction;
}
//Calculate P=P_0+t∙v 

public Point3D getPoint(double t) {
	 return Util.isZero(t ) ? start : start.add(direction.scale(t));
	
   }
@Override
public boolean equals(Object obj) {
   if (this == obj)
      return true;
   if (obj == null)
      return false;
   if (getClass() != obj.getClass())
      return false;
   Ray other = (Ray) obj;
   if (direction == null) {
      if (other.direction != null)
         return false;
   } else if (!direction.equals(other.direction))
      return false;
   if (start == null) {
      if (other.start != null)
         return false;
   } else if (!start.equals(other.start))
      return false;
   return true;
}
}


