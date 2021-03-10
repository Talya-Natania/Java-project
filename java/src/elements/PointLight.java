/**
 * 
 */
package elements;

import java.util.ArrayList;
import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author USER
 *
 */
public class PointLight extends Light implements LightSource {

	/**
	 * fields
	 * @param _intensity
	 */
	protected Point3D _position;
	protected double kC,kL,kQ;
	 protected double SizeOfSquare;
	/**
	 * 
	 * @param _intensity
	 */
	public PointLight(Color _intensity, Point3D position, double _kC, double _kL, double _kQ) {
		super(_intensity);
		// TODO Auto-generated constructor stub
	        this._position = new Point3D(position);
	        this.kC = _kC;
	        this.kL = _kL;
	        this.kQ = _kQ;
	        SizeOfSquare=4;
	}
    // by default, the constant attenuation value is 1 and the other two values are 0
    public PointLight(Color colorIntensity, Point3D position) {
        this(colorIntensity, position, 1d, 0d, 0d);
    }

	@Override
	public Color getIntensity(Point3D p) {
		double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        return (_intensity.reduce(kC + kL * d + kQ * dsquared));
	}

	@Override
	public Vector getL(Point3D p) {
		if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalize();
	}
	@Override
	public double getDistance(Point3D point) {
		return point.distance(_position);
	}
	private Vector Ortognal(Vector l) {
		double x =Math.abs(l.getStart().getX().get());
		double y = Math.abs(l.getStart().getY().get());
		double z = Math.abs(l.getStart().getZ().get());
		if(x < y)
			if(x < z )
				return new Vector(0, -z, y);
			else
				return new Vector(-y, x, 0);
		else if (y < z)
			    return new Vector(z, 0, -x);
		    else
				return new Vector(-y, x, 0);

	}
	@Override
    public List<Point3D> getPointsGrid(Vector LightDir)
    {

	    List<Point3D> points = new ArrayList<>();
	    Vector Vright=Ortognal(LightDir).normalized();
	    Vector Vup=Vright.crossProduct(LightDir).normalized();
	    double Size = SizeOfSquare/9;
	    Point3D helper=_position;
	    for (int row = 0; row < 9; ++row) {
	        for (int column = 0; column <9 ; ++column) {
	            double Px = (row - (9/2d)) * Size + Size/2d;
	            double Py = (column - (9/2d)) * Size +Size/2d;
	            if(Px!=0) {helper = helper.add(Vright.scale(-Px));}
	            if(Py!=0) {helper = helper.add(Vup.scale(-Py));}
	            points.add(helper);
	            helper = _position;
	        }
	    }
	    return points;
    }
}
