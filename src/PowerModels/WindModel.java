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
public class WindModel implements IPowerGeneration{

	private double airDensity;
	private double windSpd;
	private double effCoeff;
	private double radius;
	private double costPerUnit;
	private int quantity;
	private Location location;
	
	public WindModel(double airDensity, double windSpd, double effCoeff){
		this.airDensity = airDensity;
		this.windSpd = windSpd;
		this.effCoeff = effCoeff;
	}
	
	public WindModel() {
	}

	public double calculatePower(){
		return .5*airDensity*(radius * radius * Math.PI)*(Math.pow(windSpd, 3))*effCoeff;
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

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public double getCostPerUnit() {
		return costPerUnit;
	}

	public void setCostPerUnit(double costPerUnit) {
		this.costPerUnit = costPerUnit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(double d) {
		this.quantity = (int) d;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
	
}
