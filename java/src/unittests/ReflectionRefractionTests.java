/**
 * 
 */
package unittests;

import java.util.Random;

import org.junit.Test;

import elements.*;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 *
 */
public class ReflectionRefractionTests {

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	//@Test
	/*public void twoSpheres() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
		scene.addGeometries(
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50,
						new Point3D(0, 0, 50)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));
		scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
				0.0004, 0.0000006));
		ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	/*@Test
	public void twoSpheresOnMirrors() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(10000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
		scene.addGeometries(
				new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
				new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));
		scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150), 
				   new Vector(-1, 1, 4), 1, 0.00001, 0.000005));
		ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing partial shadow
	 */
	/*@Test
	public void trianglesTransparentSphere() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
						30, new Point3D(60, -50, 50)));

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

		ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	@Test
	public void OurPic1() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(new Color(0,100,0), new Material(0, 0.8, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(new Color(0,100,0), new Material(0, 0.8, 60), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0,0.2), // )
						10, new Point3D(0, 0, 115)),
				new Sphere(new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 30), // )
						10, new Point3D(50, 50, 115)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						10, new Point3D(-50, -50, 115)),
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30,0.6,0), // )
						10, new Point3D(50, -50, 115)),
				new Triangle(new Color(218,165,32), new Material(0, 0.8, 60), //
						new Point3D(-50, 50, 115), new Point3D(150, 150, 135), new Point3D(200, 200, 130))
				);
		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(40,-40,-160), new Vector(-1, 1, 4), 1, 4E-4, 2E-5),
				new SpotLight(new Color(700, 400, 400), //
						new Point3D(300, 30,0), new Vector(-2, 3, 3), 1, 4E-4, 2E-5)
				);
		ImageWriter imageWriter = new ImageWriter("bil", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
		
	}*/
	@Test
	public void OurProject() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.addGeometries( 
				new Triangle(new Color(java.awt.Color.gray), new Material(0.2, 0.2, 30,0.6,0), //
						new Point3D(300, 300, 0), new Point3D(300, -300, 600), new Point3D(300, -300, 0)),
				new Triangle(new Color(java.awt.Color.gray), new Material(0.2, 0.2, 30,0.6,0), //
						new Point3D(300, 300, 0), new Point3D(300, 300, 600), new Point3D(300, -300, 600)),
				new Triangle(new Color(java.awt.Color.gray), new Material(0.2, 0.2, 30,0.6,0), //
						new Point3D(-300, 300, 0), new Point3D(-300, -300, 600), new Point3D(-300, -300, 0)),
				new Triangle(new Color(java.awt.Color.gray), new Material(0.2, 0.2, 30,0.6,0), //
						new Point3D(-300, 300, 0), new Point3D(-300, 300, 600), new Point3D(-300, -300, 600)),
				new Triangle(new Color(java.awt.Color.gray), new Material(0.2, 0.2, 30,0.6,0), //
						new Point3D(-300, 300, 0), new Point3D(300, 300, 600), new Point3D(-300, 300, 600)),
				new Triangle(new Color(java.awt.Color.gray), new Material(0.2, 0.2, 30,0.6,0), 
						new Point3D(300, 300, 0), new Point3D(300, 300, 600), new Point3D(-300, 300, 0)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30),
						20, new Point3D(250, 50, 0)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						30, new Point3D(0, -290, 0)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
								10, new Point3D(100, 200, 500)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
										25, new Point3D(-50, -50, 115)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
												10, new Point3D(-100, 100, 200)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						30, new Point3D(-120, -200, 400)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						10, new Point3D(130, 250, 100)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						15, new Point3D(0, 200, 200)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						20, new Point3D(70, 250, 100)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						10, new Point3D(-150, 200, 115)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						30, new Point3D(-200, -200, 170)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						20, new Point3D(-220, -250, 10)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
								10, new Point3D(-250, -250, 115)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						25, new Point3D(200, 200, 200)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						10, new Point3D(200, 100, 200)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						20, new Point3D(180, 200, 100)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						20, new Point3D(250, -20, 300)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						25, new Point3D(-250, -40, 100)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						20, new Point3D(250, 250, 150)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						18, new Point3D(270, 270, 300)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						20, new Point3D(100, -270, 200)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
								20, new Point3D(0, -300, 0)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						15, new Point3D(240, 100, 200)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						15, new Point3D(40, 20, 100)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30), // )
						15, new Point3D(80, 50, 150))
				);
		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(0,-300,0), new Vector(0,-1,0), 1, 4E-4, 2E-5),
				new SpotLight(new Color(700, 400, 400), //
						new Point3D(100, -200,200), new Vector(0, -1, 0), 1, 4E-4, 2E-5),
				new SpotLight(new Color(700, 400, 400), //
						new Point3D(-400,0,0), new Vector(1, 0, 0), 1, 4E-4, 2E-5),
				new SpotLight(new Color(500, 300, 0), new Point3D(-50, 100, -50),
		                new Vector(1, -1, 2), 1, 0.00001, 0.00000001),new PointLight(new Color(500, 250, 250),
		                        new Point3D(10, 10, 130),
		                        1, 0.0005, 0.0005),new DirectionalLight(new Color(300, 150, 150), new Vector(1, 1, 0.1))
				);
		ImageWriter imageWriter = new ImageWriter("project2", 600, 600, 600, 600);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.writeToImage();
		
	}
	
}
