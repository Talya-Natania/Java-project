import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import geometries.Tube;
/**
 * Targil 1 by:
 *  Talya Yazdi  talyayazdi15@gmail.com  211976295
 *  and Naama Arivi naama1arivi@gmail.com 314660168 */
/**
 * Test program for the 1st stage
 *
 * @author Dan Zilberstein
 */
public final class Main {

    /**
     * Main program to tests initial functionality of the 1st stage
     * 
     * @param args irrelevant here
     */
    public static void main(String[] args) {

     /*   try { // test zero vector
            new Vector(0, 0, 0);
            out.println("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // test length..
        if (!isZero(v1.lengthSquared() - 14))
            out.println("ERROR: lengthSquared() wrong value");
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            out.println("ERROR: length() wrong value");

        // test Dot-Product
        if (!isZero(v1.dotProduct(v3)))
            out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
        if (!isZero(v1.dotProduct(v2) + 28))
            out.println("ERROR: dotProduct() wrong value");

        // test Cross-Product
        try { // test zero vector
            v1.crossProduct(v2);
            out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
        Vector vr = v1.crossProduct(v3);
        if (!isZero(vr.length() - v1.length() * v3.length()))
            out.println("ERROR: crossProduct() wrong result length");
        if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
            out.println("ERROR: crossProduct() result is not orthogonal to its operands");

        // test vector normalization vs vector length and cross-product
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        if (vCopy != vCopyNormalize)
            out.println("ERROR: normalize() function creates a new vector");
        if (!isZero(vCopyNormalize.length() - 1))
            out.println("ERROR: normalize() result is not a unit vector");
        Vector u = v.normalized();
        if (u == v)
            out.println("ERROR: normalizated() function does not create a new vector");

        // Test operations with points and vectors
        Point3D p1 = new Point3D(1, 2, 3); 
        if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
            out.println("ERROR: Point + Vector does not work correctly");
        if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)))
             out.println("ERROR: Point - Point does not work correctly");
        Ray ray=new Ray(new Point3D(1,2,3),new Vector(0,3,0));
       // Tube tube=new Tube(5,ray);
		Point3D p=new Point3D(3,4,6);
        double t=ray.getDirection().dotProduct(new Vector(p.subtract(ray.getStart())));
        out.println(t);
		Point3D O=ray.getStart().add(ray.getDirection().scale(t));
		Vector temp=p.subtract(O).normalized();
		out.println(temp.getStart().getX().get());
		out.println(temp.getStart().getY().get());
		out.println(temp.getStart().getZ().get());
        out.println("If there were no any other outputs - all tests succeeded!");*/
  /*Point3D Po=new Point3D(0,0,3);
   Vector v=new Vector(1,2,-3);
   Point3D h=new Point3D(0, 4, 0);
   Point3D i=new Point3D(0, 1, 0);
   Point3D j=new Point3D(3, 1, 0);
   Vector v1=h .subtract(Po);
   Vector v2=i.subtract(Po);
   Vector v3=j.subtract(Po); 
Vector N1=(v1.crossProduct(v2));
Vector N2=(v2.crossProduct(v3));
Vector N3=(v1.crossProduct(v3));
double a=alignZero(v.dotProduct(N1));
double b=alignZero(v.dotProduct(N2));
double c=alignZero(v.dotProduct(N3));
		out.println(a);
		out.println(b);
		out.println(c);*/
    	//Polygon p = new Polygon(new Point3D(0, 4, 0), new Point3D(0, 1, 0), new Point3D(3, 1, 0));		
		//List<Point3D> l = (p.findIntsersections(new Ray( new Point3D(0,0,3),new Vector(2,3,-3))));
	//	System.out.println(l.get(0)+"  !");
       /* Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        System.out.println( camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6).getDirection().getStart().getX());
        System.out.println( camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6).getDirection().getStart().getY());
        System.out.println( camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6).getDirection().getStart().getZ());
        System.out.println( camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6).getStart().getX());
        System.out.println( camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6).getStart().getY());
        System.out.println( camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6).getStart().getZ());
        System.out.println( new Vector(-2,-2,10).normalized().getStart().getX());
        System.out.println( new Vector(-2,-2,10).normalized().getStart().getY());
        System.out.println( new Vector(-2,-2,10).normalized().getStart().getZ());*/
    	Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(10000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
		scene.addGeometries(
				new Triangle(new Color(java.awt.Color.blue), new Material(0.2, 0.2, 30,0.6,0), //
						new Point3D(-300, 300, 0), new Point3D(-300, -300, 600), new Point3D(-300, -300, 0)));
		scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150), 
				   new Vector(-1, 1, 4), 1, 0.00001, 0.000005));
		ImageWriter imageWriter = new ImageWriter("main", 2500, 2500, 500, 500);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
    		
    }
}
