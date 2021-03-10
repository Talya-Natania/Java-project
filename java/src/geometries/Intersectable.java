/**
 * 
 */
package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * @author USER
 *
 */
public interface Intersectable {
	List<GeoPoint> findIntsersections(Ray ray);
	 /**
     * GeoPoint is just a tuple holding
     * references to a specific point in a specific geometry
     */
	public static class GeoPoint {
	    public Geometry geometry;
	    public Point3D point;
	    /**
	     * C-tor which get geometry and point
	     */
	    public GeoPoint(Geometry _geometry, Point3D pt) {
            this.geometry = _geometry;
            this.point = pt;
        }
	    /**
	     * Getter for point
	     */

        public Point3D getPoint() {
            return point;
        }
        /**
	     * Getter for the geometry
	     */
        public Geometry getGeometry() {
            return geometry;
        }
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((geometry == null) ? 0 : geometry.hashCode());
			result = prime * result + ((point == null) ? 0 : point.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}
	}
	

}
