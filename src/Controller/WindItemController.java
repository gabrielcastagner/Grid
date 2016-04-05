package Controller;

import java.util.UUID;

import org.eclipse.swt.widgets.Button;
import PowerModels.WindModel;
import UserInterface.Elements.Table.OutputTableItem;
import PowerModels.Graph.Location;
import UserInterface.Elements.Table.WindTableItem;

public class WindItemController extends AbstractPowerItemController{

	private final WindTableItem item;				//Model's representation on main table
	private final WindModel model;					//The model which it controls
	private final UUID uuid;						//Key for controller for HashMap in Controller class
	private OutputTableItem output;					//Model's representation on output table
	
	private boolean outputted = false;				//Whether or not an output table item has been generated
	
	public WindItemController(WindTableItem item, WindModel model, UUID uuid){
		this.item = item;
		this.model = model;
		this.uuid = uuid;

	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetEfficiency(){
		model.setEffCoeff(Double.valueOf(item.getEfficiency()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetRadius(){
		model.setRadius(Double.parseDouble(item.getRadius()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetAirDensity(){
		model.setAirDensity(Double.parseDouble(item.getAirDensity()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetNumberOfTurbines(){
		model.setQuantity(Double.parseDouble(item.getNumberOfTurbines()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetCostPerUnit(){
		model.setCostPerUnit(Double.parseDouble(item.getCostPerTurbine()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetLocation(){
		model.setLocation(new Location(
				Math.round(Double.parseDouble(item.getLatitude())),
				Math.round(Double.parseDouble(item.getLongitude()))));
	}
	
	//----------------------------------Model to View-------------------------------------------//	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetEfficiency(){
		item.setEfficiency(Double.toString(model.getEffCoeff()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetAirDensity(){
		item.setAirDensity(Double.toString(model.getAirDensity()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetRadius(){
		item.setRadius(Double.toString(model.getRadius()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetNumberOfPanels(){
		item.setNumberOfTurbines(Double.toString(model.getQuantity()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetCostPerUnit(){
		item.setCostPerTurbine(Double.toString(model.getCostPerUnit()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetLocation(){
		item.setLatitude(Double.toString(model.getLocation().getLatitude()));
		item.setLongitude(Double.toString(model.getLocation().getLongitude()));
	}
	
	//-----------------------------------Output table stuff------------------------------------//
	/**
	 * Updates output table with respective data
	 */
	private void m2vOutputType(){
		output.setType(model.getType());
	}
	
	/**
	 * Updates output table with respective data
	 */
	private void m2vOutputLatLong(){
		output.setLat(Double.toString(model.getLocation().getLatitude()));
		output.setLong(Double.toString(model.getLocation().getLongitude()));
	}
	
	/**
	 * Updates output table with respective data
	 */
	private void m2vOutputPower(){
		output.setPowerOut(Double.toString(model.calculatePower()));
	}
	
	/**
	 * Updates output table with respective data
	 */
	private void m2vOutputQty(){
		output.setNumberOf(Integer.toString(model.getQuantity()));
	}
	
	/**
	 * Updates output table with respective data
	 */
	private void m2vOutputCostPerUnit(){
		output.setCostPer(Double.toString(model.getCostPerUnit()));
	}
	
	/**
	 * Updates output table with Power per Dollar
	 */
	private void m2vOutputPowerPerDollar(){
		output.setPowerPerDollar(Double.toString(returnPowerPerDollar()));
	}
	
	/**
	 * Updates output table with respective data
	 */
	public void buildOutput(OutputTableItem output){
		this.output = output;
	}
	
	/**
	 * Returns whether or not an output has been made for item, to avoid duplication
	 * @return true if it's been outputted, false if it hasn't
	 */
	public boolean outputted(){
		return outputted;
	}

	//-------------------------Functions controlling the model and View------------------------//
	/**
	 * Calls methods to record data inputted by user into model
	 */
	@Override
	public void analyze() {
		model.calculatePower();	
		v2mSetAirDensity();
		v2mSetEfficiency();
		v2mSetRadius();
		v2mSetCostPerUnit();
		v2mSetLocation();
		v2mSetNumberOfTurbines();
		
		item.getTable().layout();
		item.getTable().pack();
	}

	/**
	 * Removes item from tables
	 * @return returns hashmap key so it can be removed
	 */
	@Override
	public UUID destroy() {
		this.item.destroy();
		if(outputted)
			this.output.destroy();
		return this.uuid;
	}
	
	/**
	 * Removes from output table
	 */
	public void destroyOutput(){
		this.output.destroy();
	}

	@Override
	public Button getRemoveButton() {
		return item.getRemoveButton();
	}

	/**
	 * Updates first table with data inputted by user
	 */
	@Override
	public void updateModelStateToView() {
		m2vSetRadius();
		m2vSetEfficiency();
		m2vSetAirDensity();
		m2vSetCostPerUnit();
		m2vSetLocation();
		m2vSetNumberOfPanels();
		
		item.getTable().layout();
	}
	
	/**
	 * Updates output table with relevant data
	 */
	@Override
	public void updateOutputTable(){
		m2vOutputType();
		m2vOutputLatLong();
		m2vOutputPower();
		m2vOutputQty();
		m2vOutputCostPerUnit();
		m2vOutputPowerPerDollar();
		
		output.getTable().layout();
		
		outputted = true;
		
	}

	/**
	 * Compares total power output with another item (greatest to least)
	 * @param o item to compare against
	 * @return 1 for lesser than, 0 for equal, -1 for greater than
	 */
	@Override
	public double returnPower() {
		return model.calculatePower();
	}

	/**
	 * Returns total power divided by total cost
	 */
	public double returnPowerPerDollar() {
		return model.getPower()/(model.getCostPerUnit()*model.getQuantity());
	}
	
	/**
	 * Compares total power output with another item (greatest to least)
	 * @param o item to compare against
	 * @return 1 for lesser than, 0 for equal, -1 for greater than
	 */
	@Override
	public int compareTo(AbstractPowerItemController o) {
		if(this.returnPower() < o.returnPower())
			return 1;
		else if(this.returnPower() == o.returnPower())
			return 0;
		else
			return -1;
	}

	/**
	 * Compares total power output over total cost with another item (greatest to least)
	 * @param o item to compare against
	 * @return 1 for lesser than, 0 for equal, -1 for greater than
	 */
	@Override
	public int comparePerDollar(AbstractPowerItemController o) {
		if(this.returnPowerPerDollar() < o.returnPowerPerDollar())
			return 1;
		else if(this.returnPower() == o.returnPower())
			return 0;
		else
			return -1;
	}


	

}
