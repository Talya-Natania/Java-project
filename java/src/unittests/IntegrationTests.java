package unittests;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class IntegrationTests {

	    Camera camera1 = new Camera(Point3D.ZERO , new Vector(0, 0, 1), new Vector(0, -1, 0));
	    Camera camera2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

	    @Test
	   public void constructRayThroughPixelSphere1() {
	        //TCO1:Sphere r=1,Two intersection points
	        Sphere sphere =  new Sphere(1, new Point3D(0, 0, 3));

	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera1.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = sphere.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
             assertEquals("not good",2,count);
	}
	    @Test
	   public void constructRayThroughPixelSphere2() {
	        //TCO2:Sphere r=2.5,18 intersection points
	        Sphere sphere =  new Sphere(2.5, new Point3D(0, 0, 2.5));

	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera2.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = sphere.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
                 assertEquals("not good",18,count);
	}
	    @Test
	    public void constructRayThroughPixelSphere3() {
	        //TCO3:Sphere r=2,10 intersection points
	        Sphere sphere =  new Sphere(2, new Point3D(0, 0, 2));

	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera2.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = sphere.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
             assertEquals("not good",10,count);
	}	
	    @Test
	    public void constructRayThroughPixelSphere4() {
	        //TCO4:Sphere r=4,9 intersection points
	        Sphere sphere =  new Sphere(4, new Point3D(0, 0, 2));

	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera1.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = sphere.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
             assertEquals("not good",9,count);
	}
	    @Test
	    public void constructRayThroughPixelSphere5() {
	        //TCO5:Sphere r=0.5,no intersection points
	        Sphere sphere =  new Sphere(0.5, new Point3D(0, 0, -1));

	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera1.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = sphere.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
             assertEquals("not good",0,count);
	}  
	    @Test
	    public void constructRayThroughPixelPlane1() {
	        Plane plane=new Plane(new Point3D(0,0,5),new Point3D(1,0,5),new Point3D(3,3,5));
	        //TCO7:Plane,9 intersection points
	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera1.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = plane.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
             assertEquals("not good",9,count);
	}
	    @Test
	    public void constructRayThroughPixelPlane2() {
	        //TCO8:Plane,9 intersection points
	    	 Plane plane=new Plane(new Point3D(3,2,0), new Point3D(0,2,2), new Point3D(1.5,1,1));
	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera1.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = plane.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
             assertEquals("not good",9,count);
	}
	    @Test
	    public void constructRayThroughPixelPlane3() {
	        //TCO9:Plane,6 intersection points
	    	Plane plane=new Plane(new Point3D(3,2,0), new Point3D(0,1,8), new Point3D(1.5,1,4));
	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera1.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = plane.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
             assertEquals("not good",6,count);
	}
	    @Test
	    public void constructRayThroughPixelTriangle1() {
	        //TC10:Triangle,1 intersection points
Triangle tri=new Triangle(new Point3D(1, 1, 2), new Point3D(-1, 1, 2),new Point3D(0, -1, 2));
	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera1.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = tri.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
             assertEquals("not good",1,count);
	}  
	    @Test
	    public void constructRayThroughPixelTriangle2() {
	        //TC11:Triangle,2 intersection points
Triangle tri=new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2),new Point3D(-1, 1, 2));
	        int count = 0;
	        for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	                Ray ray = camera1.constructRayThroughPixel(3,3,j,i,1,3,3);
	                List<GeoPoint> results = tri.findIntsersections(ray);
	                if (results != null)
	                    count += results.size();
	            }
	        }
             assertEquals("not good",2,count);
	}    	     
	    
}


