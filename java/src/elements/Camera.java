/**
 * 
 */
package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class Camera {
	private Point3D location;
	private Vector vTo;
	private Vector vRight;
	private Vector vUp;
	public Point3D getLocation() {
		return location;
	}
	public Vector getvTo() {
		return vTo;
	}
	public Vector getvRight() {
		return vRight;
	}
	public Vector getvUp() {
		return vUp;
	}
	//A constructor with location parameters and two vectors ,creates a right vector.
	public Camera(Point3D myLocation,Vector v_to,Vector v_up) {
		location =myLocation;
		if(v_to.dotProduct(v_up)!=0)
		{
		   throw new IllegalArgumentException("The vectors are not orthogonal");
		}
		vRight= v_to.crossProduct(v_up).normalized();
		vUp=v_up.normalized();
		vTo=v_to.normalized();
	}
	/**
	*The function "shoots" rays to the viewing plane
	*
	*@return ray
	*/
public Ray constructRayThroughPixel (int nX,int nY,int j, int i,double screenDistance,double screenWidth, double screenHeight) {
if (Util.isZero(screenDistance)) {
throw new IllegalArgumentException("distance cannot be 0");
}
Point3D Pc = location.add(vTo.scale(screenDistance));
double Ry = screenHeight / nY;
double Rx = screenWidth / nX;
double yi = ((i - nY / 2d) * Ry + Ry / 2d);
double xj = ((j - nX / 2d) * Rx + Rx / 2d);
Point3D Pij = Pc;
if (!Util.isZero(xj)) {
Pij = Pij.add(vRight.scale(xj));
}
if (!Util.isZero(yi)) {
Pij =  Pij.add(vUp.scale(-yi));
}
Vector Vij = Pij.subtract(location);
return new Ray(location, Vij);
	}
}