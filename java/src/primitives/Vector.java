package primitives;
/**
	 * Class vector the basic class representing foundational object in geometry 
	 * of direction and size, defined by the end point (when starting point -
          First deputies).
	 * 
	 * @author 
	 */
public class Vector {
	Point3D start;

	public Point3D getStart() {
		return start;
	}/**
	C-tor receives 3 Coordinates
	*/
	public Vector(Coordinate a,Coordinate b,Coordinate c){
		Point3D pelet=new Point3D(a,b,c);
		if(pelet.equals(pelet.ZERO))
			throw new IllegalArgumentException ("ERROR! please enter another point.");
		start=pelet;
	}
	/**
	C-tor receives 3 double points
	*/
	public Vector(double a,double b,double c){
		Point3D pelet=new Point3D(a,b,c);
		if(pelet.equals(pelet.ZERO))
			throw new IllegalArgumentException ("ERROR! please enter another point.");
		start=pelet;	
	}
	/**
	C-tor receives a point
	*/
	public Vector(Point3D p){
		if(p.equals(p.ZERO))
			throw new IllegalArgumentException ("ERROR! please enter another point.");
		start=p;
	}
	/**
	copy C-tor 
	*/
	public Vector(Vector vec){
		start=vec.start;
	}
	/**
	subtraction vectors-returns new vector
	*/
	public Vector subtract(Vector vector2) {
		return (vector2.start.subtract((start)));
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}/**
	Adding vectors-returns a new vector
	*/
	public Vector add(Vector vector2) {
		return new Vector(vector2.start.add(this));
	}/**
	Vector Multiplier Multiplier - Scalar (Returns New Vector)
	*/
	public Vector scale(double t) {
		return new Vector(start.x._coord*t,start.y._coord*t,start.z._coord*t);
	}
	/**
	 *Scalar multiplication-formula of linear algebra 
	 *return the result
	 * */
	public double dotProduct(Vector vec) {
		return start.x._coord*vec.start.x._coord+start.y._coord*vec.start.y._coord+start.z._coord*vec.start.z._coord;
	}
	/**
	 * Vector Multiplication - 
	 * Returns a new vector that stands for two existing vectors - in the form of linear algebra
	*/
	public Vector crossProduct (Vector vec) {
		return new Vector(vec.start.z._coord*start.y._coord-start.z._coord*vec.start.y._coord,
				start.z._coord*vec.start.x._coord-start.x._coord*vec.start.z._coord,
				start.x._coord*vec.start.y._coord-start.y._coord*vec.start.x._coord);
	}
	/**
	 * Calculate the length of the vector squared
	*/
	public double lengthSquared() {
		return start.x._coord*start.x._coord+start.y._coord*start.y._coord+start.z._coord*start.z._coord;
	}
	/**
	 * Calculate the length of the vector */
	public double length() {
		return Math.sqrt(lengthSquared());
	}
	/**
	 * The vector normalization action that will change the vector itself
	 * return also the vector*/
	public Vector normalize() {
		double temp=length();
		start=new Point3D(start.x._coord/temp,start.y._coord/temp,start.z._coord/temp);
		return this;
	}
	/**
	 * Normalization returns a new vector normalized in the same direction as the original vector
	 * */
	public Vector normalized() {
		return new Vector(normalize());
	}
	
}
