package PowerModels;

import PowerModels.Graph.Location;

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
	private double costPerUnit;		//Cost of a Panel
	private int numberOfPanels;		//# of panels
	private Location location;		//Location of the Panel array
	
	public SolarModel(){
		this.numberOfPanels = 1;
	}
	
	public double calculatePower() {
		//return area * yield * avgExposure * plCoeff;
		return numberOfPanels;
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

	public void setNumberOfPanels(double numberOfPanels) {
		this.numberOfPanels = (int) numberOfPanels;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public double getCostPerUnit() {
		return costPerUnit;
	}

	public void setCostPerUnit(double costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	
	public double getPower(){
		return calculatePower();
	}

	public String getType() {
		return "Solar";
	}
	
	
}
