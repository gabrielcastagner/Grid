package Controller;

import java.util.UUID;
import org.eclipse.swt.widgets.Button;
import PowerModels.SolarModel;
import PowerModels.Graph.Location;
import UserInterface.Elements.Table.OutputTableItem;
import UserInterface.Elements.Table.SolarTableItem;

public class SolarItemController extends AbstractPowerItemController implements Comparable<AbstractPowerItemController>{
	
	private final SolarTableItem item;		//Model's representation on main table
	private final SolarModel model;			//The model which it controls
	private final UUID uuid;				//Key for controller for HashMap in Controller class
	private OutputTableItem output;			//Model's representation on output table
	
	private boolean outputted = false;		//Whether or not an output table item has been generated
	
	public SolarItemController(SolarTableItem item, SolarModel model, UUID uuid){
		this.item = item;
		this.model = model;
		this.uuid = uuid;
	}
	
	/**
	 * Returns total power calculated in model
	 */
	public double returnPower() {
		return model.getPower();
	}

	/**
	 * Returns total power over total cost from model
	 */
	public double returnPowerPerDollar() {
		return model.getPower()/(model.getCostPerUnit()*model.getNumberOfPanels());
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetEfficiency(){
		model.setYield(Double.valueOf(item.getEfficiency()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetPowerLossCoeff(){
		model.setPlCoeff(Double.parseDouble(item.getPowerLoss()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetArea(){
		model.setArea(Double.parseDouble(item.getArea()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetNumberOfPanels(){
		model.setNumberOfPanels(Double.parseDouble(item.getNumberOfPanels()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetCostPerUnit(){
		model.setCostPerUnit(Double.parseDouble(item.getCostPerUnit()));
	}
	
	/**
	 * Gets input from user for the model
	 */
	private void v2mSetLocation(){
		model.setLocation(new Location(
				Math.round(Double.parseDouble(item.getLatitude())),
				Math.round(Double.parseDouble(item.getLongitude()))));
	}
	
	//----------------------------Model to View-----------------------------------//
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetEfficiency(){
		item.setEfficiency(Double.toString(model.getYield()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetPowerLossCoeff(){
		item.setPowerLoss(Double.toString(model.getPlCoeff()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetArea(){
		item.setArea(Double.toString(model.getArea()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetNumberOfPanels(){
		item.setNumberOfPanels(Double.toString(model.getNumberOfPanels()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetCostPerUnit(){
		item.setCostPerUnit(Double.toString(model.getCostPerUnit()));
	}
	
	/**
	 * Updates main table with respective data
	 */
	private void m2vSetLocation(){
		item.setLatitude(Double.toString(model.getLocation().getLatitude()));
		item.setLongitude(Double.toString(model.getLocation().getLongitude()));
	}
	
	//--------------------------Output table stuff--------------------------------//
	/**
	 * Generates a new row on the output table
	 */
	public void buildOutput(OutputTableItem output){
		this.output = output;
	}
	
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
		output.setNumberOf(Integer.toString(model.getNumberOfPanels()));
	}
	
	/**
	 * Updates output table with respective data
	 */
	private void m2vOutputCostPerUnit(){
		output.setCostPer(Double.toString(model.getCostPerUnit()));
	}
	
	/**
	 * Returns whether or not an output has been made for item, to avoid duplication
	 * @return true if it's been outputted, false if it hasn't
	 */
	public boolean outputted(){
		return outputted;
	}
	
	//----------------Functions controlling the model and View--------------------//
	/**
	 * Removes item from tables
	 * @return returns hashmap key so it can be removed
	 */
	@Override
	public UUID destroy(){
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
	
	/**
	 * Pass the button to the main controller
	 */
	@Override
	public Button getRemoveButton(){
		return item.getRemoveButton();
	}
	
	/**
	 * Calls methods to record data inputted by user into model
	 */
	@Override
	public void analyze() {
		v2mSetArea();
		v2mSetEfficiency();
		v2mSetPowerLossCoeff();
		v2mSetNumberOfPanels();
		v2mSetCostPerUnit();
		v2mSetLocation();
		
		model.calculatePower();	
		item.getTable().layout();
		//item.getTable().pack();
	
	}

	/**
	 * Updates first table with data inputted by user
	 */
	@Override
	public void updateModelStateToView() {
		m2vSetArea();
		m2vSetEfficiency();
		m2vSetPowerLossCoeff();
		m2vSetNumberOfPanels();
		m2vSetCostPerUnit();
		m2vSetLocation();
		
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
		
		output.getTable().layout();
		
		outputted = true;
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
	public int comparePerDollar(AbstractPowerItemController o){
		if(this.returnPowerPerDollar() < o.returnPowerPerDollar())
			return 1;
		else if(this.returnPower() == o.returnPower())
			return 0;
		else
			return -1;
	}

	

}
