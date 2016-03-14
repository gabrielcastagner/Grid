package PowerModels;

/**
 * 
 * @author Gabriel Castagner
 * @author Ethan Chan
 * @author Siming Ma
 * 
 *
 */
public class SolarModel implements IPowerGeneration{

	private double airDensity;
	private double windSpd;
	private double effCoeff;
	
	public SolarModel(double airDensity, double windSpd, double effCoeff){
		this.airDensity = airDensity;
		this.windSpd = windSpd;
		this.effCoeff = effCoeff;
	}
	
	public double calculatePower(double area){
		return .5*airDensity*area*(Math.pow(windSpd, 3))*effCoeff;
	}

	public double timeToPayOff() {
		return 0;
	}

	public double getAirDensity() {
		return airDensity;
	}

	public void setAirDensity(double airDensity) {
		this.airDensity = airDensity;
	}

	public double getWindSpd() {
		return windSpd;
	}

	public void setWindSpd(double windSpd) {
		this.windSpd = windSpd;
	}

	public double getEffCoeff() {
		return effCoeff;
	}

	public void setEffCoeff(double effCoeff) {
		this.effCoeff = effCoeff;
	}
	
	
}
