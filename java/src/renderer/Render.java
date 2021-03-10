package renderer;
import java.util.ArrayList;
import java.util.List;

import elements.Camera;
import elements.DirectionalLight;
import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;
/*The class render 'create' the scene*/
public class Render {
	/**
	 * fields*/
	ImageWriter _imageWriter;
	Scene _scene;
	private static final double DELTA = 0.1;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private int _threads = 1;
	private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
	private boolean _print = false; // printing progress percentage
private	int MAX_LEVEL=10;
	/**
	* Set multithreading <br>
	* - if the parameter is 0 - number of coress less SPARE (2) is taken
	* @param threads number of threads
	* @return the Render object itself
	*/
public Render setMultithreading(int threads) {
	if (threads < 0) 
		throw new IllegalArgumentException("Multithreading must be 0 or higher");
	if (threads != 0) 
		_threads = threads;
	else {
	int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
	_threads = cores <= 2 ? 1 : cores;
	}
	return this;
	}
/**
* Set debug printing on
* 
* @return the Render object itself
*/


public Render setDebugPrint() { _print = true; return this; }
	//c-tor
 public Render(Scene _scene)
 {
	        this._scene = _scene;
 }
    //another c-tor
public Render(ImageWriter imageWriter, Scene scene)
	 {
	        this._imageWriter = imageWriter;
	        this._scene = scene;
	 }
public void printGrid(int interval, java.awt.Color color) {
	        int Nx = _imageWriter.getNx();
	        int Ny = _imageWriter.getNy();
	        for (int i = 0; i < Ny; i++) {
	            for (int j = 0; j < Nx; j++) {
	                if (i % interval == 0 || j % interval == 0) {
	                    _imageWriter.writePixel(j, i, color);
	                }
	            }
	        }
	    }

public void writeToImage() {
	   _imageWriter.writeToImage();
	        }
	    /**
	    * Pixel is an internal helper class whose objects are associated with a Render object that
	    * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	    * its progress.<br/>
	    * There is a main follow up object and several secondary objects - one in each thread.
	    */
	    private class Pixel {
	    private long _maxRows = 0; // Ny
	    private long _maxCols = 0; // Nx
	    private long _pixels = 0; // Total number of pixels: Nx*Ny
	    public volatile int row = 0; // Last processed row
	    public volatile int col = -1; // Last processed column
	    private long _counter = 0; // Total number of pixels processed
	    private int _percents = 0; // Percent of pixels processed
	    private long _nextCounter = 0; // Next amount of processed pixels for percent progress
	    /**
	    * The constructor for initializing the main follow up Pixel object
	    * @param maxRows the amount of pixel rows
	    * @param maxCols the amount of pixel columns
	    */
	    public Pixel(int maxRows, int maxCols) {
	    _maxRows = maxRows;_maxCols = maxCols; _pixels = maxRows * maxCols;
	    _nextCounter = _pixels / 100;
	    if (Render.this._print) System.out.printf("\r %02d%%", _percents);
	    }
	    /**
	    * Default constructor for secondary Pixel objects
	    */
	    public Pixel() {}
	    /**
	    * Public function for getting next pixel number into secondary Pixel object.
	    * The function prints also progress percentage in the console window.
	    * @param target target secondary Pixel object to copy the row/column of the next pixel
	    * @return true if the work still in progress, -1 if it's done
	    */
	    public boolean nextPixel(Pixel target) {
	    int percents = nextP(target);
	    if (_print && percents > 0) System.out.printf("\r %02d%%", percents);
	    if (percents >= 0) return true;
	    if (_print) System.out.printf("\r %02d%%", 100);
	    return false;
	    }
	    /**
	    * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
	    * critical section for all the threads, and main Pixel object data is the shared data of this critical
	    * section.<br/>
	    * The function provides next pixel number each call.
	    * @param target target secondary Pixel object to copy the row/column of the next pixel
	    * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
	    * finished, any other value - the progress percentage (only when it changes)
	    */
	    private synchronized int nextP(Pixel target) {
	    ++col; ++_counter;
	    if (col < _maxCols) {
	    target.row = this.row; target.col = this.col;
	    if (_print && _counter == _nextCounter) {
	    ++_percents;_nextCounter = _pixels * (_percents + 1) / 100; return _percents;
	    }
	    return 0;
	    }
	    ++row;
	    if (row < _maxRows) {
	    col = 0;
        target.row = this.row;
        target.col = this.col;
	    if (_print && _counter == _nextCounter) {
	    ++_percents; _nextCounter = _pixels * (_percents + 1) / 100; return _percents;
	    }
	    return 0;
	    }
	    return -1;
	    }
	    }
	    public Color averageColors(List<Color> colors) {
	        double red = 0, green = 0, blue = 0;
	        for (var color:colors) {
	            red += color.getColor().getRed();
	            green += color.getColor().getGreen();
	            blue += color.getColor().getBlue();
	        }
	        red = red/colors.size();
	        green = green/colors.size();
	        blue = blue/colors.size();
	        if(red>255)
	            red= 255;
	        if(green>255)
	            green= 255;
	        if(blue>255)
	            blue= 255;
	        return  new Color(red,green, blue);
	    }

	    /**
	     * this function calculates the average color for the pixel
	     * @param center center of the pixel
	     * @param width width of the pixel
	     * @param height height of the pixel
	     * @param level level of recorsya
	     * @param colors list of colors from the levels
	     * @return the color of the pixel
	     */
	    public Color RecorsyaGetColor(Ray center, double width, double height, int level,List<Color> colors)
	    {
	        var rays = getFiveRays(center,width,height);
	        List<Color> Newcolors = new ArrayList<>();
	        for (Ray ray:rays) {
	            Newcolors.add(getColor(ray));
	        }
	        boolean isEqualColors = true;
	        for (Color color:Newcolors) {
	            if(!color.getColor().equals(Newcolors.get(0).getColor())) {
	                isEqualColors = false;
	            }
	        }
	        colors.addAll(Newcolors);
	        if(isEqualColors)
	            return averageColors(Newcolors);
	        if(level == MAX_LEVEL)
	            return averageColors(Newcolors);
	        //recorsya
	        for (Ray NewCenter:FourCenters(width,height,center.getStart())) {
	            colors.add(RecorsyaGetColor(NewCenter, width/2, height/2, level+1,colors));
	        }
	        return averageColors(colors);
	    }
	    public List<Ray> getFiveRays(Ray rayCenter, double width, double height) {
	        List<Ray> rays = new ArrayList<Ray>();
	        try {
	            Point3D p0 = rayCenter.getStart();
	            Point3D NewPoint = new Point3D(p0.getX().get() + width / 2, p0.getY().get() + height / 2, p0.getZ().get());
	            rays.add(new Ray(NewPoint, _scene.getCamera().getLocation().subtract(NewPoint)));
	            NewPoint = new Point3D(p0.getX().get() + width / 2, p0.getY().get() - height / 2, p0.getZ().get());
	            rays.add(new Ray(NewPoint, _scene.getCamera().getLocation().subtract(NewPoint)));
	            NewPoint = new Point3D(p0.getX().get() - width / 2, p0.getY().get() + height / 2, p0.getZ().get());
	            rays.add(new Ray(NewPoint, _scene.getCamera().getLocation().subtract(NewPoint)));
	            NewPoint = new Point3D(p0.getX().get() - width / 2, p0.getY().get() - height / 2, p0.getZ().get());
	            rays.add(new Ray(NewPoint, _scene.getCamera().getLocation().subtract(NewPoint)));
	            rays.add(rayCenter);
	            return rays;
	        }catch (Exception e){return rays;}
	    }
	    public Color getColor(Ray ray) {
	        Color color = new Color();
	        List<GeoPoint> temp = _scene.getGeometries().findIntsersections(ray);
	        List<GeoPoint> intersectionPoints = new ArrayList<GeoPoint>();
	        if(temp!=null)
	        intersectionPoints.addAll(temp);
	        if (intersectionPoints.size() == 0) {
	            color = new Color(_scene.getBackground());
	        } else {
	            GeoPoint closestPoint = getClosestPoint(intersectionPoints);
	            color = new Color(calcColor(closestPoint, ray).getColor());
	        }
	        return color;
	    }
	    public List<Ray> FourCenters(double width, double height, Point3D center) {
	        List<Ray> rays = new ArrayList<>();
	        try{
	        Point3D NewCenter = new Point3D(center.getX().get() + width/4, center.getY().get() + height/4,center.getZ().get());
	        rays.add(new Ray(NewCenter, _scene.getCamera().getLocation().subtract(NewCenter)));
	        NewCenter = new Point3D(center.getX().get() + width/4, center.getY().get() - height/4,center.getZ().get());
	        rays.add(new Ray(NewCenter, _scene.getCamera().getLocation().subtract(NewCenter)));
	        NewCenter = new Point3D(center.getX().get() - width/4, center.getY().get() + height/4,center.getZ().get());
	        rays.add(new Ray(NewCenter, _scene.getCamera().getLocation().subtract(NewCenter)));
	        NewCenter = new Point3D(center.getX().get() - width/4, center.getY().get() - height/4,center.getZ().get());
	        rays.add(new Ray(NewCenter, _scene.getCamera().getLocation().subtract(NewCenter)));
	        return rays;}
	        catch(Exception e){return rays;}
	    }
	    
	   
	 /**
	  * Rendering pseudocode
	  */
	    
public void renderImage() {
        Camera camera = _scene.getCamera();
        double distance = _scene.getDistance();
        //width and height are the number of pixels in the rows
        //and columns of the view plane
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();
        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx(); //columns
        int Ny = _imageWriter.getNy(); //rows
        final Pixel thePixel = new Pixel(Ny, Nx); // Main pixel management object
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) { // create all threads
        threads[i] = new Thread(() -> {
        Pixel pixel = new Pixel(); // Auxiliary thread’s pixel object
        while (thePixel.nextPixel(pixel)) {
        	@SuppressWarnings("unchecked")
        	//List<Ray> rays = (List)new ArrayList<Ray>();
        	Ray r= camera.constructRayThroughPixel(Nx, Ny, pixel.col, pixel.row, distance, width, height);
        	List<Color> colors = new ArrayList<Color>();
        	java.awt.Color pixelColor = RecorsyaGetColor(r, (double)width/Nx, (double)height/Ny, 0,colors).getColor();
        	//rays.add(r);
        //	_imageWriter.writePixel(pixel.col, pixel.row, calcColor(rays).getColor());
        	_imageWriter.writePixel(pixel.col, pixel.row,pixelColor );

        }});
        }
        for (Thread thread : threads) thread.start(); // Start all the threads
        // Wait for all threads to finish
        for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
        if (_print) System.out.printf("\r100%%\n"); // Print 100%
	}
private GeoPoint getClosestPoint (List<GeoPoint> intersectionPoints) {
	       GeoPoint result = null;
	        Point3D p0 = _scene.getCamera().getLocation();
	        double minDist = Double.MAX_VALUE;
	        double currentDistance = 0;
	        for (GeoPoint geoPoint : intersectionPoints) {
	            currentDistance = p0.distance(geoPoint.getPoint());
	            if (currentDistance < minDist) {
	                minDist = currentDistance;
	                result = geoPoint;
	            }
	        }
	        return result;
}
private Color calcColor(List<Ray> inRay) {
    Color bkg = _scene.getBackground();
    Color color = Color.BLACK;
    for (Ray ray : inRay) {
        GeoPoint gp = findCLosestIntersection(ray);
        color = color.add(gp == null ? bkg : calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1d));
    }
    color = color.add(_scene.getAmbientLight().get_intensity());
    int size = inRay.size();
    return (size == 1) ? color : color.reduce(size);
}
private Color calcColor(GeoPoint gp, Ray ray) {
		 Color color = calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1.0);
	        color = color.add(_scene.getAmbientLight().get_intensity());
	        return color;	
	}
	//Adds the effect of light sources on the point for which the color is calculated using the simple Pong model
private Color calcColor(GeoPoint geoPoint,Ray inRay, int level, double k) {
		 if (level == 1) {
	            return Color.BLACK;
	        }
	        Point3D pointGeo = geoPoint.getPoint();
	        Geometry geometryGeo = geoPoint.getGeometry();
	        Color color = geometryGeo.get_emmission();
	        Material material = geometryGeo.get_material();
	        int nShininess = material.get_nShininess();
	        double kd = material.get_kD();
	        double ks = material.get_kS();
	        Vector v = pointGeo.subtract(_scene.getCamera().getLocation()).normalize();
	        Vector n = geometryGeo.getNormal(pointGeo);
	        if (_scene.get_lights() != null) {
	            for (LightSource lightSource : _scene.get_lights()) {
	                Vector l = lightSource.getL(pointGeo);
	                double nl = n.dotProduct(l);
	                double nv = n.dotProduct(v);
	                double ktr;
	                if (nl * nv > 0) {
	                	if(lightSource instanceof DirectionalLight)
	                	    ktr = transparency(lightSource,l,n,geoPoint);
	                	else
	                        ktr = softShadow(lightSource,l,n,geoPoint);
	                    if (ktr * k > MIN_CALC_COLOR_K) {
	                        Color lightIntensity = lightSource.getIntensity(pointGeo).scale(ktr);
	                        color = color.add(
	                                calcDiffusive(kd, nl, lightIntensity),
	                                calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
	                    }
	                }
	            }
	        }
	        double kr = geometryGeo.get_material().get_kR();
	        double kkr = k * kr;
	        if (kkr > MIN_CALC_COLOR_K) {
	        	Ray reflectedRay = constructReflectedRay( geoPoint.point, inRay,n);
	        	GeoPoint reflectedPoint = findCLosestIntersection(reflectedRay);
	        	if (reflectedPoint != null)
	        	color = color.add(calcColor(reflectedPoint, reflectedRay,
	        	level-1, kkr).scale(kr));
	        }
	        double kt = geometryGeo.get_material().get_kT();
	        double kkt = k * kt;
	        if (kkt > MIN_CALC_COLOR_K) {
	        		Ray refractedRay = constructRefractedRay(geoPoint.point, inRay,n) ;
	        		GeoPoint refractedPoint = findCLosestIntersection(refractedRay);
	        		if (refractedPoint != null)
	        		color = color.add(calcColor(refractedPoint, refractedRay,
	        		level-1, kkt).scale(kt));
	        }
	        return color;
}
private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
	        Vector lightDirection = l.scale(-1); // from point to light source
	        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
	        Point3D pointGeo = geopoint.getPoint();
	        List<GeoPoint> intersections = _scene.getGeometries().findIntsersections(lightRay);
	        if (intersections == null) {
	            return 1d;
	        }
	        double lightDistance = light.getDistance(pointGeo);
	        double ktr = 1d;
	        for (GeoPoint gp : intersections) {
	            if (Util.alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0) {
	                ktr *= gp.getGeometry().get_material().get_kT();
	                if (ktr < MIN_CALC_COLOR_K) {
	                    return 0.0;
	                }
	            }
	        }
	        if(ktr>1)
	        	ktr=1;
	        return ktr;
	    }
private double softShadow(LightSource light, Vector l, Vector n, GeoPoint geopoint)
{
	double sum = 0;
    for(Point3D point : light.getPointsGrid(l))
    {
        l = geopoint.getPoint().subtract(point);
        sum += transparency(light, l, n, geopoint);
    }
    return (double)sum/81;
}
	  private Color calcDiffusive(double kd, double nl, Color ip) {
	        return ip.scale(Math.abs(nl) * kd);
	    }
private boolean sign(double val){
		// TODO Auto-generated method stub
		 return (val > 0d);
	}
private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector V, int nShininess, Color ip) {
        double p = nShininess;
        if (Util.isZero(nl)) {
            throw new IllegalArgumentException("nl cannot be Zero for scaling the normal vector");
        }
        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double VR = Util.alignZero(V.dotProduct(R));
        if (VR >= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        // [rs,gs,bs]ks(-V.R)^p
        return ip.scale(ks * Math.pow(-1d * VR, p));
}
private boolean unshaded (LightSource light,Vector l, Vector n, GeoPoint gp) {
    	Vector lightDirection = l.scale(-1); // from point to light source
    	Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
    	Point3D point = gp.point.add(delta);
    	Ray lightRay = new Ray(point, lightDirection,n);
    	List<GeoPoint> intersections = _scene.getGeometries().findIntsersections(lightRay);
    	if (intersections==null) return true;
    	double lightDistance = light.getDistance(gp.point);
    	for (GeoPoint geop : intersections) {
    	if (Util.alignZero(geop.point.distance(gp.point)-lightDistance) <= 0&&gp.geometry.get_material().get_kT() == 0)
    	return false;
    	}
    	return true;
}
private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getDirection(), n);
    }
private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) {
        Vector v = inRay.getDirection();
        double vn = v.dotProduct(n);
        if (vn == 0) {
            return null;
        }
        Vector r = v.subtract(n.scale(2 * vn));
      return new Ray(pointGeo, r, n);
    }
private GeoPoint findCLosestIntersection(Ray ray) {
    	 if (ray == null) {
             return null;
         }
    	    GeoPoint result = null;
	        Point3D point=ray.getStart();
	       if( _scene.getGeometries().findIntsersections(ray)==null)
	    	   return null;
	        double minDist = Double.MAX_VALUE;
	        double currentDistance = 0;
	        for (GeoPoint geoPoint :  _scene.getGeometries().findIntsersections(ray)) {
	            currentDistance = point.distance(geoPoint.getPoint());
	            if (currentDistance < minDist) {
	                minDist = currentDistance;
	                result = geoPoint;
	            }
	        }
	return result;
    }
}