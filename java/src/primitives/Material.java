package primitives;
/**
 * @author USER
 *
 */
public class Material {
	private double _kD;
    private double _kS;
    private double _kT;	
    private double _kR;
    private int _nShininess;
	/**
	 * 
	 * @param _kD
	 * @param _kS
	 * @param _nShininess
	 * @param _kT
	 * @param _kR
	 */
	public Material(double kD, double kS, int nShininess,double kT,double kR) {
		this._kD = kD;
		this._kS = kS;
		this._nShininess = nShininess;
		this._kT=kT;
		this._kR=kR;
	}
	 /**
     * 
     * @param _kD
     * @param _kS
     * @param _nShininess
     */
	public Material(double kD, double kS, int nShininess) {
	   this(kD,kS, nShininess,0,0);
	}
	public Material(Material m) {
        this(m._kD, m._kS, m._nShininess, m._kT, m._kR);
	}
	/**
	 * @return the _kD
	 */
	public double get_kD() {
		return _kD;
	}
	/**
	 * @return the _kS
	 */
	public double get_kS() {
		return _kS;
	}
	/**
	 * @return the _nShininess
	 */
	public int get_nShininess() {
		return _nShininess;
	}
	 /**
		 * @return the _kT
		 */
   public double get_kT() {
			return _kT;
		}
		/**
		 * @return the _kR
		 */
	public double get_kR() {
			return _kR;
		}
    

}
