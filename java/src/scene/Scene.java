package scene;
import java.util.LinkedList;
import java.util.List;

/*The scene class describes the characteristics of the scene.*/
import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

public class Scene {
	//fields:
	String _name;
	Color _background;
	AmbientLight _ambientLight;
	Geometries _geometries;
	Camera _camera;
	double _distance;
	List<LightSource> _lights=null; 
	public String getName() {
		return _name;
	}
	//c-tor (get just the name)
	public Scene(String _sceneName)
	{
	        this._name = _sceneName;
	        _geometries=new Geometries();//create an empty list.
    }
	//getters:
	public Color getBackground() {
		return _background;
	}
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}
	public Geometries getGeometries() {
		return _geometries;
	}
	/**
	 * @param _camera the _camera to set
	 */
	public void setCamera(Camera _camera) {
		this._camera = _camera;
	}
	/**
	 * @param _distance the _distance to set
	 */
	public void setDistance(double _distance) {
		this._distance = _distance;
	}
	/**
	 * @param _lights the _lights to set
	 */
	public void set_lights(List<LightSource> _lights) {
		this._lights = _lights;
	}
	public Camera getCamera() {
		return _camera;
	}
	public double getDistance() {
		return _distance;
	}
	
	 //setters:
	public void setBackground(Color _background) {
		this._background = _background;
	}
	public void setAmbientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}
	public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i : intersectables) {
            _geometries.add(i);
        }
    }
	public void addLights(LightSource... lights) {
		  if (_lights == null) {
	            _lights = new LinkedList<>();
	        }
	        for (LightSource i : lights) {
	        	_lights.add(i);
	        	}
	 }
	public List<LightSource> get_lights() {
		return _lights;
	}
	
	
	
}
