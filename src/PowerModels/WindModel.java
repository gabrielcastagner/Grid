package PowerModels;

/**
 * 
 * @author Gabriel Castagner
 * @author Ethan Chan
 * @author Siming Ma
 * 
 *
 */
public class WindModel implements IPowerGeneration{

	private double yield;
	private double avgExposure;
	private double plCoeff;
	
	public WindModel(double yield, double avgExposure, double plCoeff){
		this.yield = yield;
		this.avgExposure = avgExposure;
		this.plCoeff = plCoeff;
	}
	
	public double calculatePower(double area) {
		return area * yield * avgExposure * plCoeff;
	}

	public double timeToPayOff() {
		return 0;
	}

	public double getYield() {
		return yield;
	}

	public void setYield(double yield) {
		this.yield = yield;
	}

	public double getAvgExposure() {
		return avgExposure;
	}

	public void setAvgExposure(double avgExposure) {
		this.avgExposure = avgExposure;
	}

	public double getPlCoeff() {
		return plCoeff;
	}

	public void setPlCoeff(double plCoeff) {
		this.plCoeff = plCoeff;
	}

}
