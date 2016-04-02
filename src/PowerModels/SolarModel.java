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

	private double yield;			//% yield for power conversion
	private double avgExposure;		//Average Solar Exposure in w/m^2
	private double plCoeff;			//PowerLoss Coefficient
	private double area;			//Single panel area
	private int numberOfPanels;		//Single panel area
	
	
	public SolarModel(){
		this.numberOfPanels = 1;
	}
	
	public SolarModel(double yield, double avgExposure, double plCoeff){
		this.yield = yield;
		this.avgExposure = avgExposure;
		this.plCoeff = plCoeff;
	}
	
	public double calculatePower() {
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

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getNumberOfPanels() {
		return numberOfPanels;
	}

	public void setNumberOfPanels(int numberOfPanels) {
		this.numberOfPanels = numberOfPanels;
	}
	
	
}
