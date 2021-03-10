package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> _vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane _plane;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     * 
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex></li>
     *                                  </ul>
     */
    public Polygon(Color emmission,Material m,Point3D... vertices) {
    	super(emmission,m);
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        _vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        _plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3) return; // no need for more tests for a Triangle
        Vector n = _plane.get_normal();
        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);
        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }
    public Polygon(Color emissionLight, Point3D... vertices) {
        this(emissionLight, new Material(0, 0, 0), vertices);
    }
    public Polygon( Point3D... vertices) {
    	this(Color.BLACK,new Material(0, 0, 0),vertices);
    	
    }
    @Override
    public Vector getNormal(Point3D point) {
        return _plane.getNormal(null);
    }

@Override
public List<GeoPoint> findIntsersections(Ray ray) {
		List<GeoPoint> l=_plane.findIntsersections(ray);
		if(l==null)
			return null;
		try {
		Point3D Po=ray.getStart();
		Vector v=ray.getDirection();
		Vector v1=_vertices.get(0).subtract(Po);
		Vector v2=_vertices.get(1).subtract(Po);
		Vector v3=_vertices.get(2).subtract(Po);
		double a=v.dotProduct(v1.crossProduct(v2));
		 if (isZero(a)) return null;
		double b=v.dotProduct(v2.crossProduct(v3));
		 if (isZero(b)) return null;
		double c=v.dotProduct(v3.crossProduct(v1));
		 if (isZero(c)) return null;
		 List<GeoPoint> helper=new ArrayList<GeoPoint>();
		 for(var point:_plane.findIntsersections(ray)) {
				helper.add(new GeoPoint(this,point.getPoint()));
			}
  return ((a > 0 && b > 0 && c > 0) || (a < 0 && b < 0 && c < 0)) ? helper : null;
		}
		catch(IllegalArgumentException e) {
			return null;
		}
	}
}
